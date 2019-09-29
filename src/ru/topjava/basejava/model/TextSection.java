package ru.topjava.basejava.model;

public class TextSection extends Section {
    private final String content;

    public TextSection(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "content='" + content + '\'' +
                '}';
    }
}
