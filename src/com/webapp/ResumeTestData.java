package com.webapp;

import com.webapp.model.*;

import java.util.EnumMap;

public class ResumeTestData {
    public ResumeTestData() {
    }

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

        EnumMap<SectionType, AbstractSection> sections = resume.getSections();

        contacts.forEach((k, v) -> System.out.println(k.getTitle() + " " + v));
        sections.forEach((k, v) -> System.out.println(k.getTitle() + " \n" + v.getBlockDescriptions()));
    }
}
