package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.urise.webapp.model.ContactType.*;
import static com.urise.webapp.model.SectionType.*;
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
        RESUME_2.addContact(PHONE, "+2222222");
        RESUME_2.addContact(E_MAIL, "dsff@yandex.ru");
        RESUME_3.addContact(SKYPE, "333333333@33");
        RESUME_3.addContact(E_MAIL, "3333@yandex.ru");
        RESUME_4.addContact(E_MAIL, "4444444@yandex.ru");
        RESUME_5.addContact(PHONE, "+555555555");
        RESUME_5.addContact(SKYPE, "55555.555");
        RESUME_5.addContact(E_MAIL, "55555@yandex.ru");

        RESUME_1.addSection(PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность." +
                " Пурист кода и архитектуры."));
        RESUME_1.addSection(OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и " +
                "Enterprise технологиям."));

        RESUME_2.addSection(PERSONAL, new TextSection("Персональная информация №2."));
        RESUME_2.addSection(OBJECTIVE, new TextSection("Персональные достижения №2"));

        RESUME_5.addSection(PERSONAL, new TextSection("Персональная информация №5."));
        RESUME_5.addSection(OBJECTIVE, new TextSection("Персональные достижения №5"));

        List<String> Achievement1 = new ArrayList<>();
        Achievement1.add("С 2013 года: разработка проектов.");
        Achievement1.add("Реализация аутентификации.");
        RESUME_1.addSection(ACHIEVEMENT, new ListSection(Achievement1));

        List<String> Achievement2 = new ArrayList<>();
        Achievement2.add("Это 1 достижение.");
        Achievement2.add("Это 2 достижение.");
        RESUME_2.addSection(ACHIEVEMENT, new ListSection(Achievement2));

        List<String> Achievement5 = new ArrayList<>();
        Achievement5.add("Это 1 достижение.");
        Achievement5.add("Это 2 достижение.");
        RESUME_5.addSection(ACHIEVEMENT, new ListSection(Achievement5));

        List<String> Qualification = new ArrayList<>();
        Qualification.add("JEE AS: GlassFish (v2.1, v3)");
        Qualification.add("Version control:");
        Qualification.add("DB: PostgreSQL(наследование)");
        RESUME_1.addSection(QUALIFICATIONS, new ListSection(Qualification));

        List<String> Qualification2 = new ArrayList<>();
        Qualification2.add("Это 1 квалификация");
        Qualification2.add("Это 2 квалификация");
        Qualification2.add("Это 3 квалификация");
        RESUME_2.addSection(QUALIFICATIONS, new ListSection(Qualification2));

        List<String> Qualification5 = new ArrayList<>();
        Qualification5.add("Это 1 квалификация");
        Qualification5.add("Это 2 квалификация");
        Qualification5.add("Это 3 квалификация");
        RESUME_5.addSection(QUALIFICATIONS, new ListSection(Qualification5));

        List<Organization> Experience = new ArrayList<>();
        Experience.add(new Organization("Java Online Projects", "http://javaops.ru/",
                new Organization.Position(2013, Month.NOVEMBER, "Автор проекта.",
                        "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        Experience.add(new Organization("Wrike", "https://www.wrike.com/",
                new Organization.Position(2014, Month.NOVEMBER, 2016, Month.JANUARY, "Старший разработчик (backend)", "Проектирование и разработка " +
                        "онлайн платформы управления проектами Wrike.")));
        RESUME_1.addSection(EXPERIENCE, new OrganizationSection(Experience));
        RESUME_2.addSection(EXPERIENCE, new OrganizationSection(Experience));

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
        RESUME_2.addSection(EDUCATION, new OrganizationSection(Education));
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
        resume.addContact(PHONE, "+3333333");

        resume.addSection(PERSONAL, new TextSection("Аналитический склад ума."));
        resume.addSection(OBJECTIVE, new TextSection("Ведущий стажировок."));

        List<String> Achievement = new ArrayList<>();
        Achievement.add("Это 1 достижение.");
        Achievement.add("Это 2 достижение.");
        resume.addSection(ACHIEVEMENT, new ListSection(Achievement));

        List<String> Qualification = new ArrayList<>();
        Qualification.add("Это 1 квалификация");
        Qualification.add("Это 2 квалификация");
        Qualification.add("Это 3 квалификация");
        resume.addSection(QUALIFICATIONS, new ListSection(Qualification));
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