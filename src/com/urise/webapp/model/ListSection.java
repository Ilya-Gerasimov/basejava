package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private List<String> contents;

    public ListSection() {
    }

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
    public String toStringSection() {
        StringBuilder stroka = new StringBuilder();
        for (String str : contents) {
            stroka.append(str).append("\n");
        }
        return stroka.toString();
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
