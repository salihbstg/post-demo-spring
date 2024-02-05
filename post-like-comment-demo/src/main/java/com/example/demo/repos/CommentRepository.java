package com.example.demo.repos;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Transactional
    void deleteByPost(Post post);
}
