package com.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Номер телефона:"),
    SKYPE("Skype:"),
    EMAIL("Email-почта:"),
    LINKED_IN("LinkedIn:"),
    GIT_HUB("GitHub:"),
    STACKOVERFLOW("Stackoverflow:"),
    HOME_SITE("Home site:");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
