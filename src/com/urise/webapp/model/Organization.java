package com.urise.webapp.model;

import java.time.YearMonth;
import java.util.Objects;

public class Organization {
    private final String nameUrl;
    private final String url;
    private final String title;
    private final String description;
    private final YearMonth startDate;
    private final YearMonth endDate;

    public Organization(String nameUrl, String url, String title, String description, YearMonth startDate, YearMonth endDate) {
        Objects.requireNonNull(nameUrl, "nameUrl cannot be null");
        Objects.requireNonNull(title, "title cannot be null");
        Objects.requireNonNull(startDate, "startDate cannot be null");
        Objects.requireNonNull(endDate, "endDate cannot be null");
        this.nameUrl = nameUrl;
        this.url = url;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "nameUrl='" + nameUrl + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!nameUrl.equals(that.nameUrl)) return false;
        if (url != null ? url.equals(that.url) : that.url != null) return false;
        if (!title.equals(that.title)) return false;
        if (description != null ? description.equals(that.description) : that.description != null) return false;
        if (!startDate.equals(that.startDate)) return false;
        return endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        int result = nameUrl.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }
}
