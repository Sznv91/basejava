package ru.topjava.basejava;

import ru.topjava.basejava.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;

public class ResumeTestData {

    private static Resume r1;
    private static Resume r1WithoutSection;
    private static Resume r4WithoutSection;
    public ResumeTestData(String uuid, String fullName) {
        fillResume(uuid, fullName);
    }

    public Resume getR1() {
        return r1;
    }

    public Resume getR1WithoutSection() {
        return r1WithoutSection;
    }

    public Resume getR4WithoutSection() {
        return r4WithoutSection;
    }

    private void fillResume(String uuid, String fullName) {
        r1 = new Resume(uuid, fullName);
        r1WithoutSection = new Resume(uuid, fullName);
        r4WithoutSection = new Resume("UUID_4", "Daniel");

        r1.setContact(ContactType.PHONE, "+7(921) 855-0482");
        r1.setContact(ContactType.SKYPE, "grigory.kislin");
        r1.setContact(ContactType.EMAIL, "gkislin@yandex.ru");
        r1.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        r1.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        r1.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        r1.setContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        r1WithoutSection.setContact(ContactType.PHONE, "+7(921)");
        r1WithoutSection.setContact(ContactType.SKYPE, "grigory");
        r1WithoutSection.setContact(ContactType.EMAIL, "gkislin@");
        r1WithoutSection.setContact(ContactType.LINKEDIN, "/in/gkislin");
        r1WithoutSection.setContact(ContactType.GITHUB, "gkislin");
        r1WithoutSection.setContact(ContactType.STACKOVERFLOW, "users/548473");
        r1WithoutSection.setContact(ContactType.HOMEPAGE, "gkislin.ru/");

        r4WithoutSection.setContact(ContactType.PHONE, "+7(921) 855");
        r4WithoutSection.setContact(ContactType.SKYPE, "kislin");
        r4WithoutSection.setContact(ContactType.EMAIL, "@yandex.ru");
        r4WithoutSection.setContact(ContactType.LINKEDIN, "https://www.linkedin.com");
        r4WithoutSection.setContact(ContactType.GITHUB, "https://github.com/");
        r4WithoutSection.setContact(ContactType.STACKOVERFLOW, "users/548473");
        r4WithoutSection.setContact(ContactType.HOMEPAGE, "http://");

        TextSection personal = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        TextSection objective = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

        ListSection achievements = new ListSection(new ArrayList<>(Arrays.asList("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников."
                , "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk."
                , "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера."
                , "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга."
                , "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django)."
                , "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.")));

        ListSection qualification = new ListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 "
                , "Version control: Subversion, Git, Mercury, ClearCase, Perforce"
                , "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,"
                , "MySQL, SQLite, MS SQL, HSQLDB"
                , "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,"
                , "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,"
                , "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)."
                , "Python: Django."
                , "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js"
                , "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka"
                , "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT."
                , "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,"
                , "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer."
                , "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования"
                , "Родной русский, английский \"upper intermediate\"");

        Organization.Position p1 = new Organization.Position(YearMonth.of(2013, 10), YearMonth.now(), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Organization c1 = new Organization("Java Online Projects", "http://javaops.ru/", p1);

        Organization.Position p2 = new Organization.Position(YearMonth.of(2014, 10), YearMonth.of(2016, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Organization c2 = new Organization("Wrike", "https://www.wrike.com/", p2);

        Organization.Position p3 = new Organization.Position(YearMonth.of(2012, 4), YearMonth.of(2014, 10), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Organization c3 = new Organization("RIT Center", p3);

        Organization.Position p4 = new Organization.Position(YearMonth.of(2010, 4), YearMonth.of(2012, 4), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        Organization c4 = new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/", p4);

        Organization.Position p5 = new Organization.Position(YearMonth.of(2008, 6), YearMonth.of(2010, 12), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        Organization c5 = new Organization("Yota", "https://www.yota.ru/", p5);

        Organization.Position p6 = new Organization.Position(YearMonth.of(2007, 3), YearMonth.of(2008, 6), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        Organization c6 = new Organization("Enkata", "http://enkata.com/", p6);

        Organization.Position p7 = new Organization.Position(YearMonth.of(2005, 1), YearMonth.of(2007, 2), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        Organization c7 = new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html", p7);

        Organization.Position p8 = new Organization.Position(YearMonth.of(1997, 9), YearMonth.of(2005, 1), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        Organization c8 = new Organization("Alcatel", "http://www.alcatel.ru/", p8);

        CompanySection experience = new CompanySection();
        experience.addCompany(c1);
        experience.addCompany(c2);
        experience.addCompany(c3);
        experience.addCompany(c4);
        experience.addCompany(c5);
        experience.addCompany(c6);
        experience.addCompany(c7);
        experience.addCompany(c8);

        Organization.Position p9 = new Organization.Position(YearMonth.of(2013, 3), YearMonth.of(2013, 5), "\"Functional Programming Principles in Scala\" by Martin Odersky");
        Organization c9 = new Organization("Coursera", "https://www.coursera.org/course/progfun", p9);

        Organization.Position p10 = new Organization.Position(YearMonth.of(2011, 3), YearMonth.of(2011, 4), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        Organization c10 = new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", p10);

        Organization.Position p11 = new Organization.Position(YearMonth.of(2005, 1), YearMonth.of(2005, 1), "3 месяца обучения мобильным IN сетям (Берлин)");
        Organization c11 = new Organization("Siemens AG", "http://www.siemens.ru/", p11);

        Organization.Position p12 = new Organization.Position(YearMonth.of(1997, 9), YearMonth.of(1998, 3), "6 месяцев обучения цифровым телефонным сетям (Москва)");
        Organization c12 = new Organization("Alcatel", "http://www.alcatel.ru/", p12);

        Organization.Position p13 = new Organization.Position(YearMonth.of(1993, 9), YearMonth.of(1996, 7), "Аспирантура (программист С, С++)");
        Organization.Position p13_1 = new Organization.Position(YearMonth.of(1987, 9), YearMonth.of(1993, 7), "Инженер (программист Fortran, C)");
        Organization c13 = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/", p13, p13_1);

        Organization.Position p14 = new Organization.Position(YearMonth.of(1984, 9), YearMonth.of(1987, 6), "Закончил с отличием");
        Organization c14 = new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/", p14);

        CompanySection education = new CompanySection();
        education.addCompany(c9);
        education.addCompany(c10);
        education.addCompany(c11);
        education.addCompany(c12);
        education.addCompany(c13);
        education.addCompany(c14);

        r1.setSection(SectionType.PERSONAL, personal);
        r1.setSection(SectionType.OBJECTIVE, objective);
        r1.setSection(SectionType.ACHIEVEMENT, achievements);
        r1.setSection(SectionType.QUALIFICATIONS, qualification);
        r1.setSection(SectionType.EXPERIENCE, experience);
        r1.setSection(SectionType.EDUCATION, education);
    }
}
