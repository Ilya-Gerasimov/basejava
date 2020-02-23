package com.urise.webapp.model;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private List<Position> positions;

    public Organization(String name, String url, Position... positions) {
        this.homePage = new Link(name, url);
        this.positions = Arrays.asList(positions);
    }

    @Override
    public String toString() {
        return "\nOrganization{" +
                "homePage=" + homePage +
                ", \nposition=" + positions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        return positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + positions.hashCode();
        return result;
    }

    public static class Position {
        private final String title;
        private final String description;
        private final YearMonth startDate;
        private final YearMonth endDate;

        public Position(String title, String description, YearMonth startDate, YearMonth endDate) {
            Objects.requireNonNull(title, "title cannot be null");
            Objects.requireNonNull(startDate, "startDate cannot be null");
            Objects.requireNonNull(endDate, "endDate cannot be null");
            this.title = title;
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public String toString() {
            return "\nPosition{" +
                    "title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", startDate=" + startDate +
                    ", endDate=" + endDate +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position position = (Position) o;

            if (!title.equals(position.title)) return false;
            if (description != null ? description.equals(position.description) : position.description != null)
                return false;
            if (!startDate.equals(position.startDate)) return false;
            return endDate.equals(position.endDate);
        }

        @Override
        public int hashCode() {
            int result = title.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            result = 31 * result + startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            return result;
        }
    }
}