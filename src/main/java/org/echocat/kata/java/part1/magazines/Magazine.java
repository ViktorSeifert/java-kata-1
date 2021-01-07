package org.echocat.kata.java.part1.magazines;

import org.echocat.kata.java.part1.authors.Author;

import java.text.*;
import java.util.*;
import java.util.stream.Collectors;

public class Magazine {
    private String title;
    private String isbn;
    private String authors;
    private String publishedAt;

    private final List<Author> resolvedAuthors = new LinkedList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    String getAuthors() {
        return authors;
    }

    void setAuthors(String authors) {
        this.authors = authors;
    }

    public List<Author> getAllAuthors() {
        return resolvedAuthors;
    }

    public void setAllAuthors(List<Author> authors) {
        setAuthors(authors.stream().map(Author::getEmail).collect(Collectors.joining(",")));
    }

    void resolveAuthors(List<Author> allAuthors) {
        resolvedAuthors.clear();
        for (Author author : allAuthors) {
            if (getAuthors().contains(author.getEmail())) {
                resolvedAuthors.add(author);
            }
        }
    }

    String getPublishedAt() {
        return publishedAt;
    }

    void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getPublishedAtDate() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(getPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magazine book = (Magazine) o;
        return Objects.equals(title, book.title) && Objects.equals(isbn, book.isbn) && Objects.equals(authors, book.authors) && Objects.equals(publishedAt, book.publishedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, isbn, authors, publishedAt);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors='" + resolvedAuthors + '\'' +
                ", publishedAT='" + getPublishedAtDate() + '\'' +
                '}';
    }
}
