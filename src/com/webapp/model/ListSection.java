package com.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends AbstractSection {

    private final List<String> descriptions = new ArrayList<>();

    public ListSection() {
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void addDescription(String description) {
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
}