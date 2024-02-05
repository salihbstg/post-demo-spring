package com.example.demo.repos;

import com.example.demo.entities.Like;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {
    @Transactional
    void deleteByPost(Post post);

    List<Like> findByPost(Post post);

    List<Like> findByUser(User user);
}
