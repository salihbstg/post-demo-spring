package com.example.demo.dto.comment;

import lombok.Data;

@Data
public class GetCommentDTO {
    private Long id;
    private String postUsername;
    private String postTitle;
    private String postText;
    private String username;
    private String text;
}
