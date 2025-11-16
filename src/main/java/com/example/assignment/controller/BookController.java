package com.example.assignment.controller;

import com.example.assignment.dto.BookCopyDTO;
import com.example.assignment.dto.BookDTO;
import com.example.assignment.dto.response.BookResponse;
import com.example.assignment.model.Book;
import com.example.assignment.service.BookCopyService;
import com.example.assignment.service.BookService;
import com.example.assignment.service.RestClientConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    BookCopyService bookCopyService;

    @Autowired
    RestClientConnect restClientConnect;

    @GetMapping("/{database-number}/get-all-books")
    public List<BookResponse> getBooks(@PathVariable("database-number") Integer databaseNumber){
        if (databaseNumber == 1){
            return bookService.viewAllBooks();
        }
        return restClientConnect.showAllBook(databaseNumber);
    }

    @PostMapping("/{database-number}/add-new-book")
    public Book addNewBook(@PathVariable("database-number") Integer databaseNumber, @RequestBody BookDTO bookDTO){
        if (databaseNumber == 1){
            return bookService.addNewBook(bookDTO);
        }
        return restClientConnect.addNewBook(databaseNumber,bookDTO);
    }

    @PutMapping("/{database-number}/update-title/{book-id}")
    public void updateABook(@PathVariable("database-number") Integer databaseNumber,
                            @PathVariable("book-id")Integer bookId,
                            @RequestBody String title){
        if (databaseNumber == 1){
            bookService.updateBookTitle(bookId,title);
        }
        restClientConnect.updateBookTitle(databaseNumber,bookId,title);
    }

    @PutMapping("/update-copy/{copy-id}")
    public void updateCopy(@PathVariable("copy-id")Integer copyId, @RequestBody BookCopyDTO copyDTO){
        bookCopyService.udpate(copyId,copyDTO.getVersion(),copyDTO.getPublishDate());
    }
}
