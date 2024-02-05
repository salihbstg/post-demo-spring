package com.example.demo.controller;

import com.example.demo.business.LikeManager;
import com.example.demo.dto.like.LikeDTO;
import com.example.demo.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeManager likeManager;

    @GetMapping
    public ResponseEntity<List<LikeDTO>> findAll(){
        return new ResponseEntity<>(likeManager.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{likeId}")
    public ResponseEntity<LikeDTO> findById(@PathVariable Long likeId){
        LikeDTO likeDTO=likeManager.findById(likeId);
        if(likeDTO==null){
            throw new ApplicationException("Like not found!");
        }
        return new ResponseEntity<>(likeDTO,HttpStatus.OK);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<LikeDTO>> findByPost(@PathVariable Long postId){
        List<LikeDTO> likeDTOList=likeManager.findByPost(postId);
        if(likeDTOList==null){
            throw new ApplicationException("Like not found!");
        }
        return new ResponseEntity<>(likeDTOList,HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LikeDTO>> findByUser(@PathVariable Long userId){
        List<LikeDTO> likeDTOList=likeManager.findByUser(userId);
        if(likeDTOList==null){
            throw new ApplicationException("Like not found!");
        }
        return new ResponseEntity<>(likeDTOList,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<LikeDTO> createLike(@RequestBody LikeDTO likeDTO){
        return new ResponseEntity<>(likeManager.saveLike(likeDTO),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLike(@PathVariable Long id){
        return new ResponseEntity<>(likeManager.deleteLike(id),HttpStatus.OK);
    }

}
