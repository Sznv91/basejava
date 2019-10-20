package ru.topjava.basejava.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListSection extends AbstractSection {
    private final List<String> content;

    public ListSection(List<String> content) {
        this.content = content;
    }

    public ListSection(String... item) {
        content = new ArrayList<>(Arrays.asList(item));
    }

    @Override
    public String toString() {
        String result = "";
        for (String item : content){
            result += item + System.lineSeparator();
        }
        result = result.substring(0, result.length()-2);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }
}
