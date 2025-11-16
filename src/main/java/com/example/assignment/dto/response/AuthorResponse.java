package com.example.assignment.dto.response;

import lombok.Data;

@Data
public class AuthorResponse {
    Integer id;
    String name;

    String email;

    boolean isPrimary;
}
