package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @Column(name="title")
    private String title;
    @Column(name="text")
    private String text;
}
