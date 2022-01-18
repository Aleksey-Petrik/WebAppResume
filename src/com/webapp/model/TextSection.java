package com.webapp.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    private final String description;

    public TextSection(String description) {
        Objects.requireNonNull(description, "Description not be Null!");
        this.description = description;
    }

    public String getDescription() {
        return description;
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
