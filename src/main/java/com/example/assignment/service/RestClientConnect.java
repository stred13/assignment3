package com.example.assignment.service;

import com.example.assignment.dto.AuthorDTOUpdate;
import com.example.assignment.dto.BookCopyDTO;
import com.example.assignment.dto.BookDTO;
import com.example.assignment.dto.response.AuthorResponse;
import com.example.assignment.dto.response.BookResponse;
import com.example.assignment.model.Book;
import com.example.assignment.model.Category;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClient;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestClientConnect {


    RestClient defaultClient = RestClient.create();
    final String host= "http://localhost:";

    public String selectDB(Integer dbNumber){
        if(dbNumber==2){
            return "8082";
        } else if(dbNumber==3){
            return "8083";
        }
        return "2";
    }

    public List<BookResponse> showAllBook(Integer dbNumber){

        return defaultClient.get()
                .uri(host+this.selectDB(dbNumber)+"/get-all-books")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public Book addNewBook(Integer dbNumber, BookDTO bookDTO){
        return defaultClient.post()
                .uri(host+this.selectDB(dbNumber)+"/add-new-book")
                .body(bookDTO)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public void updateBookTitle(Integer dbNumber, Integer bookid, String title){
        defaultClient.put()
                .uri(host+this.selectDB(dbNumber)+"/update-title/"+bookid)
                .body(title)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public void updateBookCopy(Integer dbNumber, Integer copyId, BookCopyDTO copyDTO){
        defaultClient.put()
                .uri(host+this.selectDB(dbNumber)+"/update-copy/"+copyId)
                .body(copyDTO)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public List<AuthorResponse> getAllAuthor(Integer dbNumber){
        return defaultClient.get()
                .uri(host+this.selectDB(dbNumber)+"/get-all-author")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public void updateAuthor(Integer dbNumber, Integer authorId, AuthorDTOUpdate authorDTOUpdate){
        defaultClient.put()
                .uri(host+this.selectDB(dbNumber)+"/update-author/"+authorId)
                .body(authorDTOUpdate)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public List<Category> getAllCategory(Integer dbNumber){
        return defaultClient.get()
                .uri(host+this.selectDB(dbNumber)+"/get-all-categories")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public void updateCategory(Integer dbNumber, Integer categoryId, String name){
        defaultClient.put()
                .uri(host+this.selectDB(dbNumber)+"/udpate-category/"+categoryId)
                .body(name)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

}
