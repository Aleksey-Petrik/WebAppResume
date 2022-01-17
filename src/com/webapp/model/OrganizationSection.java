package com.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationSection extends AbstractSection {

    List<Organization> organizations = new ArrayList<>();

    public OrganizationSection() {
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void addOrganization(String title, String url, List<Organization.PeriodWorks> periods) {
        organizations.add(new Organization(title, url, periods));
    }

    @Override
    public String getBlockDescriptions() {
        StringBuilder builder = new StringBuilder();

        organizations.forEach(organization -> builder.append(organization.getUrl())
                .append(organization.getTitle()));
        return builder.toString();
    }
}
