package com.example.demo.controller;

import com.example.demo.business.PostManager;
import com.example.demo.dto.post.GetPostDTO;
import com.example.demo.dto.post.PatchPostDTO;
import com.example.demo.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostManager postManager;

    //Get all posts
    @GetMapping
    public ResponseEntity<List<GetPostDTO>> findAll(){
        return new ResponseEntity<>(postManager.findAll(), HttpStatus.OK);
    }

    //Get post by post id
    @GetMapping("/{id}")
    public ResponseEntity<GetPostDTO> findById(@PathVariable Long id){
        GetPostDTO getPostDTO=postManager.findById(id);
        if(getPostDTO==null){
            throw new ApplicationException("Post not found!");
        }
        return new ResponseEntity<>(getPostDTO,HttpStatus.OK);
    }
    //Get all post from user
    @GetMapping("/user/{username}")
    public ResponseEntity<List<GetPostDTO>> findPostListByUsername(@PathVariable String username){
        List<GetPostDTO> getPostDTOList=postManager.findPostListByUsername(username);
        if(getPostDTOList.isEmpty()){
            throw new ApplicationException("Post not found!");
        }
        return new ResponseEntity<>(getPostDTOList,HttpStatus.OK);
    }

    //Create post
    @PostMapping
    public ResponseEntity<GetPostDTO> createPost(@RequestBody GetPostDTO getPostDTO){
        return new ResponseEntity<>(postManager.createPost(getPostDTO),HttpStatus.OK);
    }
    //Update post title or text by id
    @PatchMapping("/{id}")
    public ResponseEntity<GetPostDTO> updatePost(@RequestBody PatchPostDTO patchPostDTO,@PathVariable Long id){
        GetPostDTO getPostDTO=postManager.patchPost(patchPostDTO,id);
        if(getPostDTO==null){
            throw new ApplicationException("Post not found!");
        }
        return new ResponseEntity<>(getPostDTO,HttpStatus.OK);
    }
    //Delete post by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long id){
        return new ResponseEntity<>(postManager.deletePost(id),HttpStatus.OK);
    }
}
