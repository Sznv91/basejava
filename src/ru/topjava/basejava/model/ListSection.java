package ru.topjava.basejava.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListSection extends Section {
    private final List<String> content;

    public ListSection(List<String> content) {
        this.content = content;
    }

    public ListSection(String... item) {
        content = new ArrayList<>(Arrays.asList(item));
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "content=" + content +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
