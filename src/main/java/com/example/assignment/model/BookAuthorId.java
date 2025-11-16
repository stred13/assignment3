package com.example.assignment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookAuthorId implements Serializable {

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "author_id")
    private Integer authorId;
    public BookAuthorId(Integer authorId, Integer bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public BookAuthorId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BookAuthorId that = (BookAuthorId) o;
        return  Objects.equals(bookId, that.bookId)&&Objects.equals(authorId, that.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, authorId);
    }
}
