package com.example.assignment.service;

import com.example.assignment.model.BookCopy;
import com.example.assignment.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookCopyService {

    @Autowired
    BookCopyRepository bookCopyRepository;

    public void udpate(Integer id, Integer version, Date published){
        bookCopyRepository.findById(id).ifPresent(bookCopy -> {
            bookCopy.setVersion(version);
            bookCopy.setPublishDate(published);
            bookCopyRepository.save(bookCopy);
        });
    }
}
