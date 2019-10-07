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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
