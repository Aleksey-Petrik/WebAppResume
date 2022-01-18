package com.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private String title;
    private String url;
    private List<PeriodWorks> periods = new ArrayList<>();

    public Organization(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setPeriods(List<PeriodWorks> periods) {
        this.periods = periods;
    }

    public List<PeriodWorks> getPeriods() {
        return periods;
    }

    public void addPeriod(LocalDate startDate, LocalDate endDate, String position, String description) {
        Objects.requireNonNull(startDate, "Start date not be Null!");
        Objects.requireNonNull(endDate, "End date not be Null!");
        Objects.requireNonNull(position, "Position not be Null!");
        Objects.requireNonNull(description, "Description date not be Null!");
        periods.add(new PeriodWorks(startDate, endDate, position, description));
    }

    public static class PeriodWorks {
        private String position;
        private String description;
        private LocalDate startDate;
        private LocalDate endDate;

        public PeriodWorks(LocalDate startDate, LocalDate endDate, String position, String description) {
            this.position = position;
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }
    }

}
