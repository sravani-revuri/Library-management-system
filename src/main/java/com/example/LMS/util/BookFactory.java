package com.example.LMS.util;

import com.example.LMS.model.Book;

public class BookFactory {
    private static Book instance;

    private BookFactory() {
        // Private constructor to prevent instantiation
    }

    public static Book getInstance() {
        if (instance == null) {
            synchronized (BookFactory.class) {
                if (instance == null) {
                    instance = new Book();
                }
            }
        }
        return instance;
    }

    // Optional: reset or clone method to avoid modifying same instance
    public static Book createNewBook(String title, String author) {
        Book book = getInstance();
        book.setTitle(title);
        book.setAuthor(author);
        return book;
    }
}
