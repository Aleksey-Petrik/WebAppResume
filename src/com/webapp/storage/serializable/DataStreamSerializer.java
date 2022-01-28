package com.webapp.storage.serializable;

import com.webapp.model.ContactType;
import com.webapp.model.Resume;

import java.io.*;
import java.util.Map;

public class DataStreamSerializer implements SerializableStream {

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream reader = new DataInputStream(is)) {
            String uuid = reader.readUTF();
            String fullName = reader.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int countContacts = reader.readInt();
            for (int i = 0; i < countContacts; i++) {
                ContactType contactType = ContactType.valueOf(reader.readUTF());
                String contact = reader.readUTF();
                resume.addContact(contactType, contact);
            }
            return resume;
        }
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream writer = new DataOutputStream(os)) {
            writer.writeUTF(resume.getUuid());
            writer.writeUTF(resume.getUuid());
            Map<ContactType, String> contacts = resume.getContacts();
            writer.writeInt(contacts.size());
            contacts.forEach((k, v) -> {
                try {
                    writer.writeUTF(k.getTitle());
                    writer.writeUTF(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
