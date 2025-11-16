package com.example.assignment.service;

import com.example.assignment.dto.BookDTO;
import com.example.assignment.dto.response.BookResponse;
import com.example.assignment.model.Book;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
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
}
