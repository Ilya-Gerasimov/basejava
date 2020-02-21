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
        List<String> toResume = new ArrayList<>();
        toResume.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. " +
                "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        toResume.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, " +
                "DuoSecurity, Google Authenticator, Jira, Zendesk.");
        toResume.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, " +
                "Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. " +
                "Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        toResume.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, " +
                "GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        toResume.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base " +
                "архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему " +
                "мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        toResume.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, " +
                "Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        r1.addSection(ACHIEVEMENT, new ListSection(toResume));

        List<String> toResume1 = new ArrayList<>();
        toResume1.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        toResume1.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        toResume1.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        toResume1.add("MySQL, SQLite, MS SQL, HSQLDB");
        toResume1.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        toResume1.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        toResume1.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, " +
                "Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, " +
                "Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        toResume1.add("Python: Django.");
        toResume1.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        toResume1.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        toResume1.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, " +
                "MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        toResume1.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        toResume1.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, " +
                "OpenCmis, Bonita, pgBouncer.");
        toResume1.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных " +
                "шаблонов, UML, функционального программирования");
        toResume1.add("Родной русский, английский \"upper intermediate\"");
        r1.addSection(QUALIFICATIONS, new ListSection(toResume1));

        List<Organization> toResume2 = new ArrayList<>();
        toResume2.add(new Organization("Java Online Projects", "http://javaops.ru/", "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок.",
                YearMonth.of(2013, 10), YearMonth.of(2020, 2)));
        toResume2.add(new Organization("Wrike", "https://www.wrike.com/", "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, " +
                        "Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация " +
                        "по OAuth1, OAuth2, JWT SSO.",
                YearMonth.of(2014, 10), YearMonth.of(2016, 1)));
        toResume2.add(new Organization("RIT Center", "", "Java архитектор",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, " +
                        "версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование " +
                        "системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка " +
                        "интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, " +
                        "экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера " +
                        "документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, " +
                        "Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote " +
                        "scripting via ssh tunnels, PL/Python",
                YearMonth.of(2012, 4), YearMonth.of(2014, 10)));
        toResume2.add(new Organization("Luxoft (Deutsche Bank)", "https://www.luxoft.com/", "Ведущий программист",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, " +
                        "GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения " +
                        "для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. " +
                        "JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.",
                YearMonth.of(2012, 10), YearMonth.of(2012, 4)));
        toResume2.add(new Organization("Yota", "https://www.yota.ru/", "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                        "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                        "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX " +
                        "клиента (Python/ Jython, Django, ExtJS)",
                YearMonth.of(2006, 6), YearMonth.of(2010, 12)));
        toResume2.add(new Organization("Enkata", "https://www.pega.com/products/pega-platform/robotic-automation", "Разработчик ПО",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) " +
                        "частей кластерного J2EE приложения (OLAP, Data mining).",
                YearMonth.of(2007, 3), YearMonth.of(2008, 6)));
        toResume2.add(new Organization("Siemens AG", "https://new.siemens.com", "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО " +
                        "на мобильной IN платформе Siemens @vantage (Java, Unix).",
                YearMonth.of(2005, 1), YearMonth.of(2007, 2)));
        toResume2.add(new Organization("Alcatel", "http://www.alcatel.ru/", "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).",
                YearMonth.of(1997, 9), YearMonth.of(2005, 1)));
        r1.addSection(EXPERIENCE, new OrganizationSection(toResume2));

        List<Organization> toResume3 = new ArrayList<>();
        toResume3.add(new Organization("Coursera", "https://www.coursera.org/learn/progfun1",
                "Functional Programming Principles in Scala by Martin Odersky", "",
                YearMonth.of(2013, 3), YearMonth.of(2013, 5)));
        toResume3.add(new Organization("Luxoft",
                "https://www.luxoft-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html",
                "\tКурс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", "",
                YearMonth.of(2011, 3), YearMonth.of(2011, 4)));
        toResume3.add(new Organization("Siemens AG", "https://new.siemens.com",
                "3 месяца обучения мобильным IN сетям (Берлин)", "",
                YearMonth.of(2005, 1), YearMonth.of(2005, 4)));
        toResume3.add(new Organization("Alcatel", "http://www.alcatel.ru/",
                "6 месяцев обучения цифровым телефонным сетям (Москва)", "",
                YearMonth.of(1997, 9), YearMonth.of(1998, 3)));
        toResume3.add(new Organization("Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики", "https://itmo.ru/ru/",
                "Аспирантура (программист С, С++)", "",
                YearMonth.of(1993, 9), YearMonth.of(1996, 7)));
        toResume3.add(new Organization("Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики", "https://itmo.ru/ru/",
                "Инженер (программист Fortran, C)", "",
                YearMonth.of(1987, 9), YearMonth.of(1993, 7)));
        toResume3.add(new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/",
                "Закончил с отличием", "",
                YearMonth.of(1987, 06), YearMonth.of(1984, 9)));
        r1.addSection(EDUCATION, new OrganizationSection(toResume3));

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
