package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.urise.webapp.model.SectionType.*;

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
                if (entry.getKey() == PERSONAL) {
                    AbstractSection section = entry.getValue();
                    dos.writeUTF(((TextSection) section).getContent());
                }
                if (entry.getKey() == OBJECTIVE) {
                    AbstractSection section = entry.getValue();
                    dos.writeUTF(((TextSection) section).getContent());
                }
                if (entry.getKey() == ACHIEVEMENT) {
                    AbstractSection section = entry.getValue();
                    List<String> lists = ((ListSection) section).getContents();
                    dos.writeInt(lists.size());
                    for (String list : lists) {
                        dos.writeUTF(list);
                    }
                }
                if (entry.getKey() == QUALIFICATIONS) {
                    AbstractSection section = entry.getValue();
                    List<String> lists = ((ListSection) section).getContents();
                    dos.writeInt(lists.size());
                    for (String list : lists) {
                        dos.writeUTF(list);
                    }
                }
                if (entry.getKey() == EXPERIENCE) {
                    AbstractSection section = entry.getValue();
                    List<Organization> lists = ((OrganizationSection) section).getOrganizations();
                    dos.writeInt(lists.size());
                    for (Organization org : lists) {
                        dos.writeUTF(org.getHomePage().getName());
                        dos.writeUTF(org.getHomePage().getUrl());
                        List<Organization.Position> pos = org.getPositions();
                        dos.writeInt(lists.size());
                        for (Organization.Position poz : pos) {
//                            dos.writeInt(poz.getStartDate().getYear());
//                            dos.writeInt(poz.getStartDate().getMonth().getValue());
//                            dos.writeInt(poz.getEndDate().getYear());
//                            dos.writeInt(poz.getStartDate().getMonth().getValue());
                            writeLocalDate(dos, poz.getStartDate());
                            writeLocalDate(dos, poz.getEndDate());
                            dos.writeUTF(poz.getTitle());
                            dos.writeUTF(poz.getDescription());
                        }
                    }
                }
//                if (entry.getKey() == EDUCATION) {
//                    AbstractSection section = entry.getValue();
//                    List<Organization> lists = ((OrganizationSection) section).getOrganizations();
//
//                    for (Organization org : lists) {
//                        dos.writeUTF(org.getHomePage().getName());
//                        dos.writeUTF(org.getHomePage().getUrl());
//                        List<Organization.Position> pos = org.getPositions();
//                        for (Organization.Position poz : pos) {
//                            dos.writeInt(poz.getStartDate().getMonthValue());
//                            dos.writeInt(poz.getStartDate().getYear());
//                            dos.writeUTF(poz.getTitle());
//                            dos.writeUTF(poz.getDescription());
//                        }
//                    }
//                }
            }
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
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
            resume.addSection(PERSONAL, new TextSection(dis.readUTF()));
            resume.addSection(OBJECTIVE, new TextSection(dis.readUTF()));

            int size1 = dis.readInt();
            List<String> Achievement = new ArrayList<>();
            for (int i = 0; i < size1; i++) {
                Achievement.add(dis.readUTF());
            }
            resume.addSection(ACHIEVEMENT, new ListSection(Achievement));

            int size2 = dis.readInt();
            List<String> Qualification = new ArrayList<>();
            for (int i = 0; i < size2; i++) {
                Qualification.add(dis.readUTF());
            }
            resume.addSection(QUALIFICATIONS, new ListSection(Qualification));


            List<Organization> Experience = new ArrayList<>();
            List<Organization.Position> item = new ArrayList<>();
            int size3 = dis.readInt();
            for (int i = 0; i < size3; i++) {
                Link homePage = new Link(dis.readUTF(), dis.readUTF());
                int size4 = dis.readInt();
                    for (int n = 0; n < size4; n++) {
//                        item.add(new Organization.Position(dis.readInt(), Month.of(dis.readInt()),
//                                dis.readInt(), Month.of(dis.readInt()) , dis.readUTF(), dis.readUTF()));
                        item.add(new Organization.Position(readLocalDate(dis), readLocalDate(dis) , dis.readUTF(), dis.readUTF()));
                    }
                Experience.add(new Organization(homePage, item));
            }
            resume.addSection(EXPERIENCE, new OrganizationSection(Experience));






            return resume;
        }
    }
}
