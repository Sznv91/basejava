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
}
