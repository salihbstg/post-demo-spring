package com.example.demo.dto.comment;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import lombok.Data;
@Data
public class CommentDTO {
    private Long id;
    private Post post;
    private User user;
    private String text;
}
