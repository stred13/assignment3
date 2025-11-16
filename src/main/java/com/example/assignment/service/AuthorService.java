package com.example.assignment.service;

import com.example.assignment.dto.AuthorDTO;
import com.example.assignment.dto.CategoryDTO;
import com.example.assignment.dto.response.AuthorResponse;
import com.example.assignment.model.Author;
import com.example.assignment.model.Category;
import com.example.assignment.repository.AuthorRepository;
import com.example.assignment.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author getAuthorById(Integer id){
        return authorRepository.findById(id).get();
    }

    public List<AuthorResponse> getAllAuthors(){
        List<AuthorResponse> authorResponseList = new ArrayList<>();
         authorRepository.findAll().forEach(author -> {
            AuthorResponse  authorResponse = new AuthorResponse();
            authorResponse.setId(author.getId());
            authorResponse.setName(author.getName());
            authorResponse.setEmail(author.getEmail());
            authorResponseList.add(authorResponse);
        });
         return authorResponseList;
    }

    public void udpateAuthor(Integer id, String email, String name){
        authorRepository.findById(id).ifPresent(author -> {
            author.setEmail(email);
            author.setName(name);
            authorRepository.save(author);
        });
    }
}
