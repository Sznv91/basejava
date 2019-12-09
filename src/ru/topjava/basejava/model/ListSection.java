package ru.topjava.basejava.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {

    public ListSection() {
    }

    private List<String> content;

    public ListSection(List<String> content) {
        this.content = content;
    }

    public ListSection(String... item) {
        content = new ArrayList<>(Arrays.asList(item));
    }

    public List<String> getContent (){
        return content;
    }

    public void addContent(String string){
        content.add(string);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String item : content) {
            result.append(item).append(System.lineSeparator());
        }
        result = new StringBuilder(result.substring(0, result.length() - 2));
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }
}
