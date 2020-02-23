package com.urise.webapp;

import com.urise.webapp.model.*;
import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.Storage;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static com.urise.webapp.model.ContactType.*;
import static com.urise.webapp.model.SectionType.*;

public class ResumeTestData {
    private final static Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Григорий Кислин");
        ARRAY_STORAGE.save(r1);

//        Добавление контактов
        r1.addContact(PHONE, "+7(921) 855-0482");
        r1.addContact(SKYPE, "grigory.kislin");
        r1.addContact(SKYPE, "gkislin@yandex.ru");
        r1.addContact(LINKEDIN, "https://www.linkedin.com/in/gkislin");
        r1.addContact(GITHUB, "https://github.com/gkislin");
        r1.addContact(STATCKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        r1.addContact(HOME_PAGE, "http://gkislin.ru/");

//        Добавляем данные в секцию TextSection
        r1.addSection(OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и " +
                "Enterprise технологиям."));
        r1.addSection(PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность." +
                " Пурист кода и архитектуры."));

//        Добавляем данные в секцию ListSection
        List<String> toAchievement = new ArrayList<>();
        toAchievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. " +
                "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        toAchievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, " +
                "DuoSecurity, Google Authenticator, Jira, Zendesk.");
        toAchievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, " +
                "Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. " +
                "Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        toAchievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, " +
                "GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        toAchievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base " +
                "архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему " +
                "мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        toAchievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, " +
                "Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        r1.addSection(ACHIEVEMENT, new ListSection(toAchievement));

        List<String> toQualification = new ArrayList<>();
        toQualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        toQualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        toQualification.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        toQualification.add("MySQL, SQLite, MS SQL, HSQLDB");
        toQualification.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        toQualification.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        toQualification.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, " +
                "Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, " +
                "Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        toQualification.add("Python: Django.");
        toQualification.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        toQualification.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        toQualification.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, " +
                "MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        toQualification.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        toQualification.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, " +
                "OpenCmis, Bonita, pgBouncer.");
        toQualification.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных " +
                "шаблонов, UML, функционального программирования");
        toQualification.add("Родной русский, английский \"upper intermediate\"");
        r1.addSection(QUALIFICATIONS, new ListSection(toQualification));

        List<Organization> toExperience = new ArrayList<>();
        toExperience.add( new Organization("Java Online Projects", "http://javaops.ru/",
                new Organization.Position("Автор проекта.", "Создание, организация и проведение Java " +
                        "онлайн проектов и стажировок.",
                        YearMonth.of(2013, 10), YearMonth.of(2020, 2))));
        toExperience.add( new Organization("Wrike", "https://www.wrike.com/",
                new Organization.Position("Старший разработчик (backend)", "Проектирование и разработка " +
                        "онлайн платформы управления проектами Wrike.",
                        YearMonth.of(2014, 10), YearMonth.of(2016, 1))));
        r1.addSection(EXPERIENCE, new OrganizationSection(toExperience));

        List<Organization> toEducation = new ArrayList<>();
        toEducation.add( new Organization("Санкт-Петербургский национальный исследовательский университет",
                "https://itmo.ru/ru/",
                new Organization.Position("Аспирантура (программист С, С++)", "",
                        YearMonth.of(1993, 9), YearMonth.of(1996, 7)),
                new Organization.Position("Инженер (программист Fortran, C)", "",
                        YearMonth.of(1987, 6), YearMonth.of(1993, 7))));
        toEducation.add( new Organization("Заочная физико-техническая школа при МФТИ",
                "http://www.school.mipt.ru",
                new Organization.Position("Аспирантура (программист С, С++)", "",
                        YearMonth.of(1984, 9), YearMonth.of(1987, 6))));
        r1.addSection(EDUCATION, new OrganizationSection(toEducation));

//        Начало печати резюме
        System.out.println("Получаем резюме");
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));

//        Выводим на печать секцию ContactType
        for (ContactType dir : ContactType.values()) {
            System.out.println(dir.getTitle() + ": " + r1.getContact(dir));
        }

//        Выводим на печать секцию SectionType
        for (SectionType dir : SectionType.values()) {
            System.out.println(dir.getTitle() + ":\n" + r1.getSection(dir));
        }
    }
}
