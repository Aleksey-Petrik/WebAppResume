package com.webapp;

import com.webapp.model.*;

import java.util.EnumMap;

public class ResumeTestData {

    public static void main(String[] args) {

        Resume resume = new Resume("Grigory Kislin");
        resume.addContact(ContactType.PHONE_NUMBER, "+7(921)855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKED_IN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GIT_HUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOME_SITE, "http://gkislin.ru/");

        EnumMap<ContactType, String> contacts = resume.getContacts();

        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

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

        EnumMap<SectionType, AbstractSection> sections = resume.getSections();

        contacts.forEach((k, v) -> System.out.println(k.getTitle() + " " + v));
        sections.forEach((k, v) -> System.out.println(k.getTitle() + " \n" + v.getBlockDescriptions()));
    }
}
