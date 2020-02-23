package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private final List<String> contents;

    public ListSection(String... contents) {
        this(Arrays.asList(contents));
    }

    public ListSection(List<String> contents) {
        Objects.requireNonNull(contents, "contents cannot be null");
        this.contents = contents;
    }

    public List<String> getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return contents.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return contents.equals(that.contents);
    }

    @Override
    public int hashCode() {
        return contents.hashCode();
    }
}
