package com.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {

    List<Organization> organizations = new ArrayList<>();

    public OrganizationSection() {
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void addOrganization(String title, String url) {
        Objects.requireNonNull(title, "Title not be Null!");
        organizations.add(new Organization(title, url));
    }

    public void addOrganization(Organization organization) {
        organizations.add(organization);
    }

    @Override
    public String getBlockDescriptions() {
        StringBuilder builder = new StringBuilder();

        organizations.forEach(organization -> builder.append(organization.getUrl())
                .append(organization.getTitle()));
        return builder.toString();
    }
}
