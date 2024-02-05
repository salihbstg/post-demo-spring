package com.example.demo.controller;

import com.example.demo.business.CommentManager;
import com.example.demo.dto.comment.GetCommentDTO;
import com.example.demo.dto.comment.PostCommentDTO;
import com.example.demo.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentManager commentManager;
    //Find all comments
    @GetMapping
    public ResponseEntity<List<GetCommentDTO>> findAll(){
        return new ResponseEntity<>(commentManager.findAll(), HttpStatus.OK);
    }

    //find comment by id
    @GetMapping("/{id}")
    public ResponseEntity<GetCommentDTO> findById(@PathVariable Long id){
        GetCommentDTO getCommentDTO=commentManager.findById(id);
        if(getCommentDTO==null){
            throw new ApplicationException("Comment not found!");
        }
        return new ResponseEntity<>(getCommentDTO, HttpStatus.OK);
    }
    //Create comment
    @PostMapping
    public ResponseEntity<Boolean> createComment(@RequestBody PostCommentDTO postCommentDTO){
        return new ResponseEntity<>(commentManager.createComment(postCommentDTO),HttpStatus.OK);
    }

    //Update comment
    @PatchMapping
    public ResponseEntity<GetCommentDTO> updateComment(@RequestParam(name = "text") String text,@RequestParam(name = "id") Long id){
        return new ResponseEntity<>(commentManager.updateComment(text,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long id){
        return new ResponseEntity<>(commentManager.deleteComment(id),HttpStatus.OK);
    }
}
