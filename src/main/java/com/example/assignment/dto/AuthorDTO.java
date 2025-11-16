package com.example.assignment.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AuthorDTO {
    String name;

    String email;

    boolean isPrimary;
}
