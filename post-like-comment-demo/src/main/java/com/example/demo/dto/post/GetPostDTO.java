package com.example.demo.dto.post;

import lombok.Data;

@Data
public class GetPostDTO {
    private Long id;
    private String username;
    private String title;
    private String text;
}
