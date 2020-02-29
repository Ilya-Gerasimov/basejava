package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> person = r.getSections();
            for (Map.Entry<SectionType, AbstractSection> entry : person.entrySet()) {
                AbstractSection section = entry.getValue();
                dos.writeUTF(entry.getKey().name());
                switch (entry.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> lists1 = ((ListSection) section).getContents();
                        dos.writeInt(lists1.size());
                        for (String list : lists1) {
                            dos.writeUTF(list);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> lists2 = ((OrganizationSection) section).getOrganizations();
                        dos.writeInt(lists2.size());
                        for (Organization org : lists2) {
                            dos.writeUTF(org.getHomePage().getName());
                            dos.writeUTF(org.getHomePage().getUrl());
                            List<Organization.Position> pos = org.getPositions();
                            dos.writeInt(lists2.size());
                            for (Organization.Position poz : pos) {
                                writeLocalDate(dos, poz.getStartDate());
                                writeLocalDate(dos, poz.getEndDate());
                                dos.writeUTF(poz.getTitle());
                                dos.writeUTF(poz.getDescription());
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            for (int n = 1; n < 7; n++){
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            }
            return resume;
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                int size = dis.readInt();
                List<String> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    list.add(dis.readUTF());
                }
                return new ListSection(list);
            case EXPERIENCE:
            case EDUCATION:
                List<Organization> organizations = new ArrayList<>();
                List<Organization.Position> positions = new ArrayList<>();
                int size1 = dis.readInt();
                for (int i = 0; i < size1; i++) {
                    Link homePage = new Link(dis.readUTF(), dis.readUTF());
                    int size4 = dis.readInt();
                    for (int n = 0; n < size4; n++) {
                        positions.add(new Organization.Position(readLocalDate(dis), readLocalDate(dis),
                                dis.readUTF(), dis.readUTF()));
                    }
                    organizations.add(new Organization(homePage, positions));
                }
                return new OrganizationSection(organizations);
            default:
                throw new IllegalStateException();
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }
}
