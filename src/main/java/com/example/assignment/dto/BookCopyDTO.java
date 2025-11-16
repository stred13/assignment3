package com.example.assignment.dto;

import com.example.assignment.model.Book;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BookCopyDTO {

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    Date publishDate;
    Integer version;
}
