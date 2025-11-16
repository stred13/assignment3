package com.example.assignment.dto.response;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BookCopyResponse {
    Integer id;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    Date publishDate;
    Integer version;
}
