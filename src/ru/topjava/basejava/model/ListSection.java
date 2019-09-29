package ru.topjava.basejava.model;

import java.util.ArrayList;

public class ListSection extends Section {
    private final ArrayList<String> content;

    public ListSection(ArrayList<String> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "content=" + content +
                '}';
    }
}
