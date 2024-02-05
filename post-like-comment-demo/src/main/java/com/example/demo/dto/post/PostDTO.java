package com.example.demo.dto.post;

import com.example.demo.entities.User;
import lombok.Data;

@Data
public class PostDTO {
    private Long id;
    private User user;
    private String title;
    private String text;
}
