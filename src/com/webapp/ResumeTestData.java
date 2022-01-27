package com.webapp;

import com.webapp.model.*;
import com.webapp.util.DateUtil;

import java.util.Map;

import static com.webapp.util.DateUtil._NOW_;

public class ResumeTestData {

    public static Resume fillResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        addContacts(resume);
        addObjectivePersonal(resume);
        addExperienceEducation(resume);
        addAchievementQualifications(resume);
        return resume;
    }

    public static void addContacts(Resume resume) {
        resume.addContact(ContactType.PHONE_NUMBER, "+7(921)855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKED_IN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GIT_HUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOME_SITE, "http://gkislin.ru/");
    }

    public static void addObjectivePersonal(Resume resume) {
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
    }

    public static void addAchievementQualifications(Resume resume) {
        ListSection sectionAchievement = new ListSection();

        sectionAchievement.addDescription(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                        "Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                        "Удаленное взаимодействие (JMS/AKKA). Организация онлайн стажировок и ведение проектов. " +
                        "Более 1000 выпускников.");
        sectionAchievement.addDescription("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        sectionAchievement.addDescription(
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                        "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                        "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                        "интеграция CIFS/SMB java сервера.");
        sectionAchievement.addDescription(
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
                        "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        sectionAchievement.addDescription(
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов " +
                        "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии " +
                        "через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга " +
                        "системы по JMX (Jython/ Django).");
        sectionAchievement.addDescription("Реализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        resume.addSection(SectionType.ACHIEVEMENT, sectionAchievement);

        ListSection sectionQualifications = new ListSection();

        sectionQualifications.addDescription("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        sectionQualifications.addDescription("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        sectionQualifications.addDescription("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        sectionQualifications.addDescription("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        sectionQualifications.addDescription(
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, " +
                        "Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, " +
                        "GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, " +
                        "Selenium (htmlelements).");
        sectionQualifications.addDescription("Python: Django.");
        sectionQualifications.addDescription("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        sectionQualifications.addDescription("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        sectionQualifications.addDescription(
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, " +
                        "DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, " +
                        "OAuth1, OAuth2, JWT.");
        sectionQualifications.addDescription("Инструменты: Maven + plugin development, Gradle, настройка Ngnix.");
        sectionQualifications.addDescription("Администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, " +
                "iReport, OpenCmis, Bonita, pgBouncer.");
        sectionQualifications.addDescription("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        sectionQualifications.addDescription("Родной русский, английский \"upper intermediate\"");

        resume.addSection(SectionType.QUALIFICATIONS, sectionQualifications);
    }

    public static void addExperienceEducation(Resume resume) {
        OrganizationSection organizationsExperience = new OrganizationSection();

        Organization organization = new Organization("Java Online Projects", "http://javaops.ru/");
        organization.addPeriod(
                DateUtil.of(2016, 11),
                _NOW_,
                "Автор проекта. ",
                "Создание, организация и проведение Java онлайн проектов и стажировок.");
        organization.addPeriod(
                DateUtil.of(2013, 10),
                DateUtil.of(2016, 10),
                "Автор проекта. ",
                "Создание, организация и проведение Java онлайн проектов и стажировок.");
        organizationsExperience.addOrganization(organization);

        organization = new Organization("Wrike", "https://www.wrike.com/");
        organization.addPeriod(DateUtil.of(2014, 10), DateUtil.of(2016, 1),
                "Старший разработчик (backend) ",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, " +
                        "MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        organizationsExperience.addOrganization(organization);

        organization = new Organization("RIT Center", "");
        organization.addPeriod(DateUtil.of(2012, 4), DateUtil.of(2014, 10),
                "Java архитектор ",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, " +
                        "ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), " +
                        "AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, " +
                        "1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                        "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. " +
                        "Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, " +
                        "OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        organizationsExperience.addOrganization(organization);

        organization = new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/");
        organization.addPeriod(DateUtil.of(2010, 12), DateUtil.of(2012, 4),
                "Ведущий программист ",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, " +
                        "Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, " +
                        "мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, " +
                        "ExtGWT (GXT), Highstock, Commet, HTML5.");
        organizationsExperience.addOrganization(organization);

        organization = new Organization("Yota", "https://www.yota.ru/");
        organization.addPeriod(DateUtil.of(2008, 6), DateUtil.of(2010, 12),
                "Ведущий специалист ",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, " +
                        "EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и " +
                        "мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        organizationsExperience.addOrganization(organization);

        organization = new Organization("Enkata", "http://enkata.com/");
        organization.addPeriod(DateUtil.of(2007, 3), DateUtil.of(2008, 6),
                "Разработчик ПО ",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей " +
                        "кластерного J2EE приложения (OLAP, Data mining).");
        organizationsExperience.addOrganization(organization);

        organization = new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html");
        organization.addPeriod(DateUtil.of(2005, 1), DateUtil.of(2007, 2),
                "Разработчик ПО ",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN " +
                        "платформе Siemens @vantage (Java, Unix).");
        organizationsExperience.addOrganization(organization);

        organization = new Organization("Alcatel", "http://www.alcatel.ru/");
        organization.addPeriod(DateUtil.of(1997, 9), DateUtil.of(2005, 1), "Инженер по аппаратному и программному тестированию ",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        organizationsExperience.addOrganization(organization);

        resume.addSection(SectionType.EXPERIENCE, organizationsExperience);

        OrganizationSection organizationsEducation = new OrganizationSection();

        organization = new Organization("Coursera", "https://www.coursera.org/course/progfun");
        organization.addPeriod(DateUtil.of(2013, 3), DateUtil.of(2013, 5), "\"Functional Programming Principles in Scala\" by Martin Odersky", "");
        organizationsEducation.addOrganization(organization);

        organization = new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366");
        organization.addPeriod(DateUtil.of(2011, 3), DateUtil.of(2011, 4), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", "");
        organizationsEducation.addOrganization(organization);

        organization = new Organization("Siemens AG", "http://www.siemens.ru/");
        organization.addPeriod(DateUtil.of(2005, 1), DateUtil.of(2005, 4), "3 месяца обучения мобильным IN сетям (Берлин)", "");
        organizationsEducation.addOrganization(organization);

        organization = new Organization("Alcatel", "http://www.alcatel.ru/");
        organization.addPeriod(DateUtil.of(1997, 9), DateUtil.of(1998, 3), "6 месяцев обучения цифровым телефонным сетям (Москва)", "");
        organizationsEducation.addOrganization(organization);

        organization = new Organization("Санкт-Петербургский национальный исследовательский " +
                "университет информационных технологий, механики и оптики", "http://www.ifmo.ru/");
        organization.addPeriod(DateUtil.of(1993, 9), DateUtil.of(1996, 7), "Аспирантура (программист С, С++)", "");
        organization.addPeriod(DateUtil.of(1987, 9), DateUtil.of(1993, 7), "Инженер (программист Fortran, C)", "");
        organizationsEducation.addOrganization(organization);

        organization = new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/");
        organization.addPeriod(DateUtil.of(1984, 9), DateUtil.of(1987, 6), "Закончил с отличием", "");
        organizationsEducation.addOrganization(organization);

        resume.addSection(SectionType.EDUCATION, organizationsEducation);
    }

    public static void main(String[] args) {

        Resume resume = fillResume("", "Grigory Kislin");

        Map<ContactType, String> contacts = resume.getContacts();
        Map<SectionType, AbstractSection> sections = resume.getSections();

        contacts.forEach((k, v) -> System.out.println(k.getTitle() + " " + v));
        //sections.forEach((k, v) -> System.out.println(k.getTitle() + " \n" + v.getBlockDescriptions()));

        for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
            SectionType type = entry.getKey();
            System.out.println(type.getTitle());
            switch (type) {
                case PERSONAL:
                case OBJECTIVE:
                    System.out.println(((TextSection) entry.getValue()).getDescription());
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    ((ListSection) entry.getValue()).getDescriptions().forEach(description -> System.out.println("-" + description));
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    ((OrganizationSection) entry.getValue()).getOrganizations().forEach(organization -> {
                        System.out.println(organization.getUrl() + " " + organization.getTitle());
                        organization.getPeriods().forEach(period -> {
                            System.out.println(DateUtil.format(period.getStartDate()) + "-" + DateUtil.format(period.getEndDate()) + " " + period.getPosition());
                            System.out.println(period.getDescription());
                        });
                    });
            }
        }
    }
}
