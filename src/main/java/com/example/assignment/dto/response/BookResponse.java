package com.example.assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    Integer id;

    String title;

    List<AuthorResponse> authors = new ArrayList<>();

    CategoryResponse category;

    List<BookCopyResponse> bookCopy = new ArrayList<>();

}
