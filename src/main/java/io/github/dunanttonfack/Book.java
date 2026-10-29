package io.github.dunanttonfack;

public class Book {
    private static int count = 0;
    private String title;
    private String author;
    private int pages;

    public Book(String title, String author, int pages) {
        if (pages <= 0 || title == null || title.isBlank()) {
            throw new IllegalArgumentException("Pages must be positive, title must be not null and not  Blank");
        }
        this.title = title;
        this.author = author;
        this.pages = pages;
        count++;
    }

    public Book(String title) {
        this(title, "Unknown", 1);
    }

    public static int getCount() {
        return count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be positive");
        }
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Title: " + title + " | Author: " + author + " | Pages: " + pages;
    }
}
