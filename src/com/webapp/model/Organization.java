package com.webapp.model;

import com.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String url;
    private List<PeriodWorks> periods = new ArrayList<>();

    public Organization() {
    }

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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class PeriodWorks implements Serializable {
        private String position;
        private String description;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;

        public PeriodWorks() {
            this(null, null, "", "");
        }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PeriodWorks that = (PeriodWorks) o;
            return Objects.equals(position, that.position) && Objects.equals(description, that.description) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, description, startDate, endDate);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(title, that.title) && Objects.equals(url, that.url) && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url, periods);
    }
}
