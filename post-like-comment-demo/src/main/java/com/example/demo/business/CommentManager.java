package com.example.demo.business;

import com.example.demo.dto.comment.GetCommentDTO;
import com.example.demo.dto.comment.PostCommentDTO;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repos.CommentRepository;
import com.example.demo.repos.PostRepository;
import com.example.demo.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentManager {
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    //Find all comments
    public List<GetCommentDTO> findAll(){
        List<Comment> commentList=commentRepository.findAll();
        List<GetCommentDTO> commentDTOList=new ArrayList<>();

        for(Comment temp:commentList){
            GetCommentDTO getCommentDTO=modelMapper.map(temp,GetCommentDTO.class);
            getCommentDTO.setPostUsername(temp.getPost().getUser().getUsername());
            getCommentDTO.setUsername(temp.getUser().getUsername());
            commentDTOList.add(getCommentDTO);
        }
        return commentDTOList;
    }

    //Find comment by id
    public GetCommentDTO findById(Long id){
        Optional<Comment> optionalComment=commentRepository.findById(id);
        if(optionalComment.isPresent()){
            Comment comment=optionalComment.get();
            GetCommentDTO getCommentDTO=modelMapper.map(comment,GetCommentDTO.class);
            getCommentDTO.setPostUsername(comment.getPost().getUser().getUsername());
            getCommentDTO.setUsername(comment.getUser().getUsername());
            return getCommentDTO;
        }
        return null;
    }
    public Boolean createComment(PostCommentDTO postCommentDTO){
        Optional<User> optionalUser=userRepository.findById(postCommentDTO.getUserId());
        Optional<Post> optionalPost=postRepository.findById(postCommentDTO.getPostId());
        if(optionalPost.isPresent() && optionalUser.isPresent()){
            Comment comment=new Comment();
            comment.setPost(optionalPost.get());
            comment.setUser(optionalUser.get());
            comment.setText(postCommentDTO.getText());
            commentRepository.save(comment);
            return true;
        }
        return false;
    }
    public GetCommentDTO updateComment(String text,Long id){
        Optional<Comment> optionalComment=commentRepository.findById(id);
        if(optionalComment.isPresent()){
            Comment comment=optionalComment.get();
            comment.setText(text);
            commentRepository.save(comment);
            GetCommentDTO getCommentDTO=modelMapper.map(comment,GetCommentDTO.class);
            getCommentDTO.setUsername(comment.getUser().getUsername());
            getCommentDTO.setPostUsername(comment.getPost().getUser().getUsername());
            return getCommentDTO;
        }
        return null;
    }
    public Boolean deleteComment(Long id){
        Optional<Comment> optionalComment=commentRepository.findById(id);
        if(optionalComment.isPresent()){
            Comment comment=optionalComment.get();
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }
}
