package com.example.demo.dto.comment;

import lombok.Data;

@Data
public class PostCommentDTO {
    private Long postId;
    private Long userId;
    private String text;
}
