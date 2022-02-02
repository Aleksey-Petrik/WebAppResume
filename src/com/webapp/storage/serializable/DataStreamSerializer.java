package com.webapp.storage.serializable;

import com.webapp.model.*;
import com.webapp.util.DateUtil;

import java.io.*;
import java.util.List;
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
        try (DataOutputStream writer = new DataOutputStream(os)) {
            writer.writeUTF(resume.getUuid());
            writer.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writer.writeInt(contacts.size());
            contacts.forEach((k, v) -> {
                try {
                    writer.writeUTF(k.name());
                    writer.writeUTF(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Map<SectionType, AbstractSection> sections = resume.getSections();
            writer.writeInt(sections.size());
            sections.forEach((k, v) -> {
                try {
                    writer.writeUTF(k.name());
                    switch (k) {
                        case OBJECTIVE:
                        case PERSONAL:
                            writer.writeUTF(((TextSection) v).getDescription());
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            List<String> descriptions = ((ListSection) v).getDescriptions();
                            writer.writeInt(descriptions.size());
                            descriptions.forEach(description -> {
                                try {
                                    writer.writeUTF(description);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            break;
                        case EDUCATION:
                        case EXPERIENCE:
                            List<Organization> organizations = ((OrganizationSection) v).getOrganizations();
                            writer.writeInt(organizations.size());
                            for (Organization organization : organizations) {
                                writer.writeUTF(organization.getTitle());
                                writer.writeUTF(organization.getUrl());
                                List<Organization.PeriodWorks> periodWorks = organization.getPeriods();
                                writer.writeInt(periodWorks.size());
                                for (Organization.PeriodWorks periodWork : periodWorks) {
                                    writer.writeUTF(DateUtil.format(periodWork.getStartDate()));
                                    writer.writeUTF(DateUtil.format(periodWork.getEndDate()));
                                    writer.writeUTF(periodWork.getPosition());
                                    writer.writeUTF(periodWork.getDescription());
                                }
                            }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @FunctionalInterface
    private interface Readable {
        void reader() throws IOException;
    }

    private void reader(DataInputStream dis, Readable reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.reader();
        }
    }
}
