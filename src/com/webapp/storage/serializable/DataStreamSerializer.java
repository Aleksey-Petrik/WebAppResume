package com.webapp.storage.serializable;

import com.webapp.model.*;
import com.webapp.util.DateUtil;

import java.io.*;
import java.util.Collection;
import java.util.Map;

public class DataStreamSerializer implements SerializableStream {

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            //Read contacts
            reader(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            //Read sections
            reader(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                AbstractSection section = null;
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        section = new TextSection(dis.readUTF());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = new ListSection();
                        reader(dis, () -> listSection.addDescription(dis.readUTF()));
                        section = listSection;
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        OrganizationSection organizationSection = new OrganizationSection();
                        reader(dis, () -> {
                            Organization organization = new Organization(dis.readUTF(), dis.readUTF());
                            reader(dis, () -> organization.addPeriod(DateUtil.parse(dis.readUTF()), DateUtil.parse(dis.readUTF()), dis.readUTF(), dis.readUTF()));
                            organizationSection.addOrganization(organization);
                        });
                        section = organizationSection;
                }
                resume.addSection(sectionType, section);
            });
            return resume;
        }
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            //Write contacts
            Map<ContactType, String> contacts = resume.getContacts();
            writer(dos, contacts.entrySet(), contact -> {
                dos.writeUTF(contact.getKey().name());
                dos.writeUTF(contact.getValue());
            });
            //Write sections
            Map<SectionType, AbstractSection> sections = resume.getSections();
            writer(dos, sections.entrySet(), section -> {
                dos.writeUTF(section.getKey().name());
                switch (section.getKey()) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(((TextSection) section.getValue()).getDescription());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writer(dos, ((ListSection) section.getValue()).getDescriptions(), dos::writeUTF);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        writer(dos, ((OrganizationSection) section.getValue()).getOrganizations(), organization -> {
                            dos.writeUTF(organization.getTitle());
                            dos.writeUTF(organization.getUrl());
                            writer(dos, organization.getPeriods(), periodWork -> {
                                dos.writeUTF(DateUtil.format(periodWork.getStartDate()));
                                dos.writeUTF(DateUtil.format(periodWork.getEndDate()));
                                dos.writeUTF(periodWork.getPosition());
                                dos.writeUTF(periodWork.getDescription());
                            });
                        });
                }
            });
        }
    }

    @FunctionalInterface
    private interface Readable {
        void reader() throws IOException;
    }

    @FunctionalInterface
    private interface Writable<T> {
        void writer(T t) throws IOException;
    }

    private void reader(DataInputStream dis, Readable reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.reader();
        }
    }

    private <T> void writer(DataOutputStream dos, Collection<T> collections, Writable<T> writer) throws IOException {
        dos.writeInt(collections.size());
        for (T element : collections) {
            writer.writer(element);
        }
    }
}
