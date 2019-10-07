package ru.topjava.basejava;

import ru.topjava.basejava.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;

public class TestGetSection {

    public static void main(String args[]) {
        Resume r1 = new Resume("Григорий Кислин");

        r1.setContact(ContactType.PHONE, "+7(921) 855-0482");
        r1.setContact(ContactType.SKYPE, "grigory.kislin");
        r1.setContact(ContactType.EMAIL, "gkislin@yandex.ru");
        r1.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        r1.setContact(ContactType.GITHUB, "Профиль GitHub");
        r1.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        r1.setContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        TextSection personal = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        TextSection objective = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

        ListSection achievements = new ListSection(new ArrayList<>(Arrays.asList("TС 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников."
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

        CompanyPeriod p1 = new CompanyPeriod(YearMonth.of(2013, 10), YearMonth.now(), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Company c1 = new Company("Java Online Projects", "http://javaops.ru/", p1);

        CompanyPeriod p2 = new CompanyPeriod(YearMonth.of(2014, 10), YearMonth.of(2016, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Company c2 = new Company("Wrike", "https://www.wrike.com/", p2);

        CompanyPeriod p3 = new CompanyPeriod(YearMonth.of(2012, 04), YearMonth.of(2014, 10), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Company c3 = new Company("RIT Center", p3);

        CompanyPeriod p4 = new CompanyPeriod(YearMonth.of(2010, 04), YearMonth.of(2012, 04), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        Company c4 = new Company("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/", p4);

        CompanyPeriod p5 = new CompanyPeriod(YearMonth.of(2008, 06), YearMonth.of(2010, 12), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        Company c5 = new Company("Yota", "https://www.yota.ru/", p5);

        CompanyPeriod p6 = new CompanyPeriod(YearMonth.of(2007, 03), YearMonth.of(2008, 06), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        Company c6 = new Company("Enkata", "http://enkata.com/", p6);

        CompanyPeriod p7 = new CompanyPeriod(YearMonth.of(2005, 01), YearMonth.of(2007, 02), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        Company c7 = new Company("Siemens AG", "https://www.siemens.com/ru/ru/home.html", p7);

        CompanyPeriod p8 = new CompanyPeriod(YearMonth.of(1997, 9), YearMonth.of(2005, 01), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        Company c8 = new Company("Alcatel", "http://www.alcatel.ru/", p8);

        CompanySection experience = new CompanySection();
        experience.addCompany(c1);
        experience.addCompany(c2);
        experience.addCompany(c3);
        experience.addCompany(c4);
        experience.addCompany(c5);
        experience.addCompany(c6);
        experience.addCompany(c7);
        experience.addCompany(c8);

        CompanyPeriod p9 = new CompanyPeriod(YearMonth.of(2013, 03), YearMonth.of(2013, 05), "\"Functional Programming Principles in Scala\" by Martin Odersky");
        Company c9 = new Company("Coursera", "https://www.coursera.org/course/progfun", p9);

        CompanyPeriod p10 = new CompanyPeriod(YearMonth.of(2011, 03), YearMonth.of(2011, 04), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        Company c10 = new Company("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", p10);

        CompanyPeriod p11 = new CompanyPeriod(YearMonth.of(2005, 01), YearMonth.of(2005, 01), "3 месяца обучения мобильным IN сетям (Берлин)");
        Company c11 = new Company("Siemens AG", "http://www.siemens.ru/", p11);

        CompanyPeriod p12 = new CompanyPeriod(YearMonth.of(1997, 9), YearMonth.of(1998, 03), "6 месяцев обучения цифровым телефонным сетям (Москва)");
        Company c12 = new Company("Alcatel", "http://www.alcatel.ru/", p12);

        CompanyPeriod p13 = new CompanyPeriod(YearMonth.of(1993, 9), YearMonth.of(1996, 7), "Аспирантура (программист С, С++)");
        CompanyPeriod p13_1 = new CompanyPeriod(YearMonth.of(1987, 9), YearMonth.of(1993, 7), "Инженер (программист Fortran, C)");
        Company c13 = new Company("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/", p13, p13_1);

        CompanyPeriod p14 = new CompanyPeriod(YearMonth.of(1984, 9), YearMonth.of(1987, 06), "Закончил с отличием");
        Company c14 = new Company("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/", p14);

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

        System.out.println(r1.getSection(SectionType.EDUCATION));
    }

}