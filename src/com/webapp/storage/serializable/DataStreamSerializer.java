package com.webapp.storage.serializable;

import com.webapp.model.*;
import com.webapp.util.DateUtil;

import java.io.*;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializableStream {

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream reader = new DataInputStream(is)) {
            String uuid = reader.readUTF();
            String fullName = reader.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = reader.readInt();
            for (int i = 0; i < size; i++) {
                ContactType contactType = ContactType.valueOf(reader.readUTF());
                String contact = reader.readUTF();
                resume.addContact(contactType, contact);
            }

            size = reader.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(reader.readUTF());
                AbstractSection section = null;
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        section = new TextSection(reader.readUTF());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int sizeDescriptions = reader.readInt();
                        section = new ListSection();
                        for (int j = 0; j < sizeDescriptions; j++) {
                            ((ListSection) section).addDescription(reader.readUTF());
                        }
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        section = new OrganizationSection();
                        int countOrganizations = reader.readInt();
                        for (int j = 0; j < countOrganizations; j++) {
                            Organization organization = new Organization(reader.readUTF(), reader.readUTF());
                            int sizePeriods = reader.readInt();
                            for (int k = 0; k < sizePeriods; k++) {
                                organization.addPeriod(DateUtil.parse(reader.readUTF()), DateUtil.parse(reader.readUTF()), reader.readUTF(), reader.readUTF());
                            }
                            ((OrganizationSection) section).addOrganization(organization);
                        }
                }
                resume.addSection(sectionType, section);
            }
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
}
