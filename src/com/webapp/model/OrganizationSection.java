package com.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }
}
