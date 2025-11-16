package com.example.assignment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Table
@Entity
public class BookAuthor implements Serializable {
    @EmbeddedId
    BookAuthorId id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    Book book;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("authorId")
    Author author;

    @Column
    Boolean primaryAuthor;

    public BookAuthor(Book book, Author author, Boolean primaryAuthor) {
        this.book = book;
        this.author = author;
        this.primaryAuthor = primaryAuthor;
        this.id = new BookAuthorId(book.getId(),author.getId());
    }

    public BookAuthor() {}
}
