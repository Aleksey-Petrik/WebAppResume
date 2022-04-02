package com.webapp.util;

import com.webapp.model.*;

import java.util.stream.Collectors;

public class HtmlUtil {

    public static String htmlContacts(String name, String contact) {
        return String.format("<b>%s</b> %s", name, contact);
    }

    public static String htmlSections(SectionType type, AbstractSection section) {
        StringBuilder html = new StringBuilder(String.format("<H3>%s</H3>", type.getTitle()));
        switch (type) {
            case PERSONAL:
            case OBJECTIVE:
                html.append(String.format("<p>%s</p>", ((TextSection) section).getDescription()));
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                html.append("<ul>");
                ((ListSection) section).getDescriptions().forEach(description -> html.append(String.format("<li>%s</li>", description)));
                html.append("</ul>");
                break;
            case EXPERIENCE:
            case EDUCATION:
                html.append("<ul>");
                ((OrganizationSection) section).getOrganizations().forEach(organization ->
                        html.append("<li>")
                                .append(String.format("<h4><a href=%s>%s</a></h4>", organization.getUrl(), organization.getTitle()))
                                .append("<ul>")
                                .append(organization.getPeriods()
                                        .stream()
                                        .map(period -> String.format("<li><b>%s-%s</b> %s<br>%s</li>", DateUtil.format(period.getStartDate()),
                                                DateUtil.format(period.getEndDate()), period.getPosition(), period.getDescription()))
                                        .collect(Collectors.joining()))
                                .append("</ul>")
                                .append("</li>")
                );
                html.append("</ul>");
                break;
        }
        return html.append("<br>").toString();
    }
}
