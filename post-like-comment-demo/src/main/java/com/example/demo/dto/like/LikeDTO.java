package com.example.demo.dto.like;

import lombok.Data;

@Data
public class LikeDTO {
    private Long id;
    private Long postId;
    private String postUsername;
    private Long likeUserId;
    private String likeUsername;
}
