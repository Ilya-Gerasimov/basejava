package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.TextSection;
import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.Storage;

import static com.urise.webapp.model.ContactType.*;
import static com.urise.webapp.model.SectionType.*;

public class ResumeTestData {
    private final static Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Григорий Кислин");

        System.out.println("Сохраняем объекты");
        ARRAY_STORAGE.save(r1);

        System.out.println("Получаем резюме");
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));

        System.out.println("Добавляем телефон");
        r1.addContact(PHONE, "+7(921) 855-0482");
        System.out.println("Get: " + PHONE.getTitle() + ": " + r1.getContact(PHONE));

        System.out.println("Добавляем Скайп");
        r1.addContact(SKYPE, "grigory.kislin");
        System.out.println("Get: " + SKYPE.getTitle() + ": " + r1.getContact(SKYPE));

        System.out.println("Добавляем Почту");
        r1.addContact(SKYPE, "gkislin@yandex.ru");
        System.out.println("Get " + E_MAIL.getTitle() + ": " + r1.getContact(E_MAIL));

        System.out.println("Добавляем профиль LinkedIn");
        r1.addContact(LINKEDIN, "https://www.linkedin.com/in/gkislin");
        System.out.println("Get " + LINKEDIN.getTitle() + ": " + r1.getContact(LINKEDIN));

        System.out.println("Добавляем профиль GitHub");
        r1.addContact(GITHUB, "https://github.com/gkislin");
        System.out.println("Get " + GITHUB.getTitle() + ": " + r1.getContact(GITHUB));

        System.out.println("Добавляем профиль Stackoverflow");
        r1.addContact(STATCKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        System.out.println("Get " + STATCKOVERFLOW.getTitle() + ": " + r1.getContact(STATCKOVERFLOW));

        System.out.println("Добавляем домашнюю страницу");
        r1.addContact(HOME_PAGE, "http://gkislin.ru/");
        System.out.println("Get " + HOME_PAGE.getTitle() + ": " + r1.getContact(HOME_PAGE));

        System.out.println("Добавляем позицию");
        r1.addSection(OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и " +
                "Enterprise технологиям."));
        System.out.println("Get: " + OBJECTIVE.getTitle() + ": " + r1.getSection(OBJECTIVE));

        System.out.println("Добавляем личные качества");
        r1.addSection(PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность." +
                " Пурист кода и архитектуры."));
        System.out.println("Get: " + PERSONAL.getTitle() + ": " + r1.getSection(PERSONAL));

        System.out.println("Добавляем достижения");
        r1.addSection(ACHIEVEMENT, new TextSection("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников."));
        System.out.println("Get: " + ACHIEVEMENT.getTitle() + ": " + r1.getSection(ACHIEVEMENT));

        System.out.println("Добавляем квалификацию");
        r1.addSection(QUALIFICATIONS, new TextSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"));
        System.out.println("Get: " + QUALIFICATIONS.getTitle() + ": " + r1.getSection(QUALIFICATIONS));

        System.out.println("Добавляем опыт работы");
        r1.addSection(EXPERIENCE, new TextSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"));
        System.out.println("Get: " + EXPERIENCE.getTitle() + ": " + r1.getSection(EXPERIENCE));


    }
}
