package com.example.assignment.controller;

import com.example.assignment.dto.AuthorDTOUpdate;
import com.example.assignment.dto.response.AuthorResponse;
import com.example.assignment.model.Author;
import com.example.assignment.service.AuthorService;
import com.example.assignment.service.RestClientConnect;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @Autowired
    RestClientConnect restClientConnect;

    @Hidden
    @GetMapping("/get-author/{author-id}")
    public Author getAuthor(@PathVariable("author-id") Integer authorId) {
        return authorService.getAuthorById(authorId);
    }

    @GetMapping("/{database-number}/get-all-author")
    public List<AuthorResponse> getAllAuthor(@PathVariable("database-number") Integer databaseNumber) {
        if (databaseNumber == 1){
            return authorService.getAllAuthors();
        }
        return restClientConnect.getAllAuthor(databaseNumber);
    }

    @PutMapping("/{database-number}/update-author/{author-id}")
    public void updateAuthor(@PathVariable("database-number") Integer databaseNumber,
                             @PathVariable("author-id")Integer id,
                             @RequestBody AuthorDTOUpdate authorDTOUpdate) {
        if (databaseNumber == 1){
            authorService.udpateAuthor(id,authorDTOUpdate.getName(),authorDTOUpdate.getEmail());
            return;
        }
        restClientConnect.updateAuthor(databaseNumber,id,authorDTOUpdate);
    }
}
