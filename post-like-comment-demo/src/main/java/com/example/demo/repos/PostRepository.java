package com.example.demo.repos;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
public List<Post> findByUser(User user);
}
