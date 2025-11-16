package com.example.assignment.controller;

import com.example.assignment.dto.AuthorDTOUpdate;
import com.example.assignment.dto.response.AuthorResponse;
import com.example.assignment.model.Author;
import com.example.assignment.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping("/get-author/{author-id}")
    public Author getAuthor(@PathVariable("author-id") Integer authorId) {
        return authorService.getAuthorById(authorId);
    }

    @GetMapping("/get-all-author")
    public List<AuthorResponse> getAllAuthor() {
        return authorService.getAllAuthors();
    }

    @PutMapping("/update-author/{author-id}")
    public void updateAuthor(@PathVariable("author-id")Integer id,@RequestBody AuthorDTOUpdate authorDTOUpdate) {
        authorService.udpateAuthor(id,authorDTOUpdate.getName(),authorDTOUpdate.getEmail());
    }
}
