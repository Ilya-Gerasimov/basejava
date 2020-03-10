package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.urise.webapp.model.ContactType.*;
import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;
    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final String UUID_5 = UUID.randomUUID().toString();

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;
    private static final Resume RESUME_5;

    static {
        RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_4 = new Resume(UUID_4, "Name4");
        RESUME_5 = new Resume(UUID_5, "Name5");
        RESUME_1.addContact(PHONE, "+7(921) 855-0482");
        RESUME_1.addContact(SKYPE, "grigory.kislin");
        RESUME_1.addContact(E_MAIL, "gkislin@yandex.ru");
        RESUME_2.addContact(PHONE, "+7(921) 855-0482");
        RESUME_2.addContact(E_MAIL, "dsff@yandex.ru");
        RESUME_3.addContact(SKYPE, "grigory.kislin");
        RESUME_4.addContact(E_MAIL, "gkislin@yandex.ru");
        RESUME_5.addContact(PHONE, "+7(921) 855-0482");
        RESUME_5.addContact(SKYPE, "grigory.kislin");
        RESUME_5.addContact(E_MAIL, "gkislin@yandex.ru");

/*        RESUME_1.addSection(PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность." +
                " Пурист кода и архитектуры."));
        RESUME_1.addSection(OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и " +
                "Enterprise технологиям."));
        List<String> Achievement = new ArrayList<>();
        Achievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. " +
                "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        Achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, " +
                "DuoSecurity, Google Authenticator, Jira, Zendesk.");
        RESUME_1.addSection(ACHIEVEMENT, new ListSection(Achievement));
        List<String> Qualification = new ArrayList<>();
        Qualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        Qualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        Qualification.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        RESUME_1.addSection(QUALIFICATIONS, new ListSection(Qualification));
        List<Organization> Experience = new ArrayList<>();
        Experience.add(new Organization("Java Online Projects", "http://javaops.ru/",
                new Organization.Position(2013, Month.NOVEMBER, 2020, Month.NOVEMBER,"Автор проекта.",
                        "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        Experience.add(new Organization("Wrike", "https://www.wrike.com/",
                new Organization.Position(2014, Month.NOVEMBER, 2016, Month.JANUARY, "Старший разработчик (backend)", "Проектирование и разработка " +
                        "онлайн платформы управления проектами Wrike.")));
        RESUME_1.addSection(EXPERIENCE, new OrganizationSection(Experience));

        List<Organization> Education = new ArrayList<>();
        Education.add(new Organization("Санкт-Петербургский национальный исследовательский университет",
                "https://itmo.ru/ru/",
                new Organization.Position(1993, Month.SEPTEMBER, 1996, Month.JULY, "Аспирантура (программист С, С++)", ""),
                new Organization.Position(1987, Month.JUNE, 1993, Month.JULY, "Инженер (программист Fortran, C)",
                        "")));
        Education.add(new Organization("Заочная физико-техническая школа при МФТИ",
                "http://www.school.mipt.ru",
                new Organization.Position(1984, Month.SEPTEMBER, 1987, Month.JUNE, "Выпускник школы", "")));
        RESUME_1.addSection(EDUCATION, new OrganizationSection(Education));
 */
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_4);
    }

    @Test
    public void size() throws Exception {
        assertSize(4);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_3);
        assertSize(3);
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_5);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(4, list.size());
        assertEquals(Arrays.asList(RESUME_1, RESUME_2, RESUME_3, RESUME_4), list);
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
        assertEquals(RESUME_2, storage.get(UUID_2));
        assertEquals(RESUME_3, storage.get(UUID_3));
        assertEquals(RESUME_4, storage.get(UUID_4));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_3, "New_Name3");
        resume.addContact(SKYPE, "grigory.kislin");
        storage.update(resume);
        assertEquals(resume, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(RESUME_5);
    }

    @Test
    public void save() {
        storage.save(RESUME_5);
        assertSize(5);
        assertEquals(RESUME_5, storage.get(UUID_5));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}