package com.example.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@RequiredArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String title;

    @OneToMany(
                    mappedBy = "book",
                    cascade = CascadeType.ALL,
                    orphanRemoval = true
            )
    List<BookAuthor> authors = new ArrayList<>();

    @ManyToOne
    Category category;

    @OneToMany
            (
                    mappedBy = "book",
                    cascade = CascadeType.ALL,
                    orphanRemoval = true
            )
    List<BookCopy> bookCopies = new ArrayList<>();

    public void addAuthor(Author author, Boolean isPrimary){
        BookAuthor bookAuthor = new BookAuthor(this,author,isPrimary);
        authors.add(bookAuthor);
        author.getBooks().add(bookAuthor);
    }

    public void addBookCopy(BookCopy bookCopy){
        bookCopies.add(bookCopy);
        bookCopy.setBook(this);
    }
}
