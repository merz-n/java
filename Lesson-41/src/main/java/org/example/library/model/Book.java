package org.example.library.model;

public class Book {
    private Long id;
    private String title;
    private int publishedYear;
    private Long authorId;

    public Book() {
    }

    public Book(Long id, String title, int published_year, Long authorId) {
        this.id = id;
        this.title = title;
        this.publishedYear = published_year;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", published_year=" + publishedYear +
                ", authorId=" + authorId +
                '}';
    }
}
