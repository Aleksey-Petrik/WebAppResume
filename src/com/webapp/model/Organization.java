package com.webapp.model;

import java.time.LocalDate;
import java.util.List;

public class Organization {
    private String title;
    private String url;
    private List<PeriodWorks> periods;

    public Organization(String title, String url, List<PeriodWorks> periods) {
        this.title = title;
        this.url = url;
        this.periods = periods;
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

    public void addPeriod(String description, LocalDate startDate, LocalDate endDate) {
        periods.add(new PeriodWorks(description, startDate, endDate));
    }

    public static class PeriodWorks {
        private String description;
        private LocalDate startDate;
        private LocalDate endDate;

        public PeriodWorks(String description, LocalDate startDate, LocalDate endDate) {
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
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
