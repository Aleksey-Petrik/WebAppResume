package com.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final List<String> descriptions = new ArrayList<>();

    public ListSection() {
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void addDescription(String description) {
        Objects.requireNonNull(description, "Description not be Null!");
        descriptions.add(description);
    }

    @Override
    public String getBlockDescriptions() {
        StringBuilder builder = new StringBuilder();
        descriptions.forEach(description -> builder.append("-")
                .append(description)
                .append("\n"));
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(descriptions, that.descriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptions);
    }
}