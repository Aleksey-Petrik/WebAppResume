package com.webapp.model;

public class TextSection extends AbstractSection {
    private final String description;

    public TextSection(String description) {
        this.description = description;
    }

    public String getBlockDescriptions() {
        return description;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "description='" + description + '\'' +
                '}';
    }
}
