package com.example.assignment.service;

import com.example.assignment.dto.BookDTO;
import com.example.assignment.dto.CategoryDTO;
import com.example.assignment.dto.response.AuthorResponse;
import com.example.assignment.dto.response.BookCopyResponse;
import com.example.assignment.dto.response.BookResponse;
import com.example.assignment.dto.response.CategoryResponse;
import com.example.assignment.model.Author;
import com.example.assignment.model.Book;
import com.example.assignment.model.BookCopy;
import com.example.assignment.model.Category;
import com.example.assignment.repository.AuthorRepository;
import com.example.assignment.repository.BookAuthorRepository;
import com.example.assignment.repository.BookRepository;
import com.example.assignment.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookAuthorRepository bookAuthorRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<BookResponse> viewAllBooks(){
        List<BookResponse> bookResponseList = new ArrayList<>();

        bookRepository.findAll().forEach(book -> {
            BookResponse bookResponse = new BookResponse();
            //book
            bookResponse.setId(book.getId());
            bookResponse.setTitle(book.getTitle());

            //category
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(book.getCategory().getId());
            categoryResponse.setName(book.getCategory().getName());
            bookResponse.setCategory(categoryResponse);

            //author
            book.getAuthors().forEach(author -> {
                AuthorResponse authorResponse = new AuthorResponse();
                authorResponse.setName(author.getAuthor().getName());
                authorResponse.setEmail(author.getAuthor().getEmail());
                authorResponse.setPrimary(author.getPrimaryAuthor());
                authorResponse.setId(author.getAuthor().getId());
                bookResponse.getAuthors().add(authorResponse);
            });

            //publish
            book.getBookCopies().forEach(bookCopy -> {
                BookCopyResponse bookCopyResponse = new BookCopyResponse();
                bookCopyResponse.setId(bookCopy.getId());
                bookCopyResponse.setVersion(bookCopy.getVersion());
                bookCopyResponse.setPublishDate(bookCopy.getPublishDate());
                bookResponse.getBookCopy().add(bookCopyResponse);
            });

            bookResponseList.add(bookResponse);
        });

        return bookResponseList;
    }

    @Transactional
    public Book addNewBook(BookDTO bookDTO){
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());

        Category category = new Category();
        category.setName(bookDTO.getCategory().getName());
        Category savedCategory = categoryRepository.save(category);
        book.setCategory(savedCategory);

        bookDTO.getAuthors().forEach(a->{
            Author author = authorRepository.save(new Author(a.getName(), a.getEmail()));
            book.addAuthor(author,a.isPrimary());
        });

        bookDTO.getBookCopy().forEach(c->{
            book.addBookCopy(new BookCopy(c.getPublishDate(),c.getVersion()));
        });

        return bookRepository.save(book);

    }

    public void updateBookTitle(Integer bookid, String title){
        bookRepository.findById(bookid).ifPresent(book -> {
            book.setTitle(title);
            bookRepository.save(book);
        });
    }

}
