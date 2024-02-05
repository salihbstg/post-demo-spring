package com.example.demo.business;

import com.example.demo.dto.post.GetPostDTO;
import com.example.demo.dto.post.PatchPostDTO;
import com.example.demo.dto.user.UserDTO;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repos.CommentRepository;
import com.example.demo.repos.LikeRepository;
import com.example.demo.repos.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostManager {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final UserManager userManager;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    public List<GetPostDTO> findAll(){
        List<Post> postList=postRepository.findAll();
        List<GetPostDTO> getPostDTOList=new ArrayList<>();
        for(Post temp:postList){
            GetPostDTO getPostDTO=modelMapper.map(temp,GetPostDTO.class);
            getPostDTO.setId(temp.getId());
            getPostDTO.setUsername(temp.getUser().getUsername());
            getPostDTOList.add(getPostDTO);
        }
        return getPostDTOList;
    }
    public GetPostDTO findById(Long id){
        Optional<Post> optionalPost=postRepository.findById(id);
        if(optionalPost.isPresent()){
            Post post=optionalPost.get();
            GetPostDTO getPostDTO=modelMapper.map(post,GetPostDTO.class);
            getPostDTO.setUsername(post.getUser().getUsername());
            return getPostDTO;
        }
        return null;
    }
    public List<GetPostDTO> findPostListByUsername(String username){
        UserDTO user=userManager.findByUsernameOrId(username);
        List<Post> postList=postRepository.findByUser(modelMapper.map(user,User.class));
        List<GetPostDTO> getPostDTOList=new ArrayList<>();
        for(Post temp:postList){
            GetPostDTO getPostDTO=modelMapper.map(temp,GetPostDTO.class);
            getPostDTO.setUsername(temp.getUser().getUsername());
            getPostDTOList.add(getPostDTO);
        }
        return getPostDTOList;
    }
    public GetPostDTO createPost(GetPostDTO getPostDTO){
        UserDTO user=userManager.findByUsernameOrId(getPostDTO.getUsername());
        Post post=modelMapper.map(getPostDTO,Post.class);
        post.setUser(modelMapper.map(user,User.class));
        postRepository.save(post);
        return findById(post.getId());
    }
    public GetPostDTO patchPost(PatchPostDTO patchPostDTO,Long id){
        Optional<Post> optionalPost=postRepository.findById(id);
        if(optionalPost.isPresent()){
            Post post=optionalPost.get();
            if(!patchPostDTO.getText().isEmpty()){
                post.setText(patchPostDTO.getText());
            }
            if(!patchPostDTO.getTitle().isEmpty()){
                post.setTitle(patchPostDTO.getTitle());
            }
            postRepository.save(post);
            GetPostDTO getPostDTO=modelMapper.map(post,GetPostDTO.class);
            getPostDTO.setUsername(post.getUser().getUsername());
            return getPostDTO;
        }
        return null;
    }
    public Boolean deletePost(Long id){
        Optional<Post> optionalPost=postRepository.findById(id);
        if(optionalPost.isPresent()){
            Post post=optionalPost.get();
            commentRepository.deleteByPost(post);
            likeRepository.deleteByPost(post);
            postRepository.delete(post);
            return true;
        }
        return false;
    }
}
