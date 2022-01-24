package com.webapp.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
