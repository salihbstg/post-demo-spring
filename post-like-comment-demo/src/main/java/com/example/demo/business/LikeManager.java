package com.example.demo.business;

import com.example.demo.dto.like.LikeDTO;
import com.example.demo.dto.post.GetPostDTO;
import com.example.demo.entities.Like;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repos.LikeRepository;
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
public class LikeManager {
    private final LikeRepository likeRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public List<LikeDTO> findAll(){
        List<Like> likeList=likeRepository.findAll();
        List<LikeDTO> likeDTOList=new ArrayList<>();
        for(Like temp:likeList){
            LikeDTO likeDTO=modelMapper.map(temp, LikeDTO.class);
            likeDTO.setPostId(temp.getPost().getId());
            likeDTO.setPostUsername(temp.getPost().getUser().getUsername());
            likeDTO.setLikeUserId(temp.getUser().getId());
            likeDTO.setLikeUsername(temp.getUser().getUsername());
            likeDTOList.add(likeDTO);
        }
        return likeDTOList;
    }
    public LikeDTO findById(Long id){
        Optional<Like> optionalLike=likeRepository.findById(id);
        if(optionalLike.isPresent()){
            Like like=optionalLike.get();
            LikeDTO likeDTO=modelMapper.map(like,LikeDTO.class);
            likeDTO.setLikeUsername(like.getUser().getUsername());
            likeDTO.setLikeUserId(like.getUser().getId());
            likeDTO.setPostId(like.getPost().getId());
            likeDTO.setPostUsername(like.getPost().getUser().getUsername());
            return likeDTO;
        }
        return null;
    }
    public List<LikeDTO> findByPost(Long postId){
        Optional<Post> optionalPost=postRepository.findById(postId);
        if(optionalPost.isPresent()){
            Post post=optionalPost.get();
            List<Like> likeList=likeRepository.findByPost(post);
            List<LikeDTO> likeDTOList=new ArrayList<>();
            for(Like temp:likeList){
                LikeDTO likeDTO=modelMapper.map(temp,LikeDTO.class);
                likeDTO.setPostId(temp.getPost().getId());
                likeDTO.setPostUsername(temp.getPost().getUser().getUsername());
                likeDTO.setLikeUserId(temp.getUser().getId());
                likeDTO.setLikeUsername(temp.getUser().getUsername());
                likeDTOList.add(likeDTO);
            }
            return likeDTOList;
        }
        return null;
    }
    public List<LikeDTO> findByUser(Long userId){
        Optional<User> optionalUser=userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            List<Like> likeList=likeRepository.findByUser(user);
            List<LikeDTO> likeDTOList=new ArrayList<>();
            for(Like temp:likeList){
                LikeDTO likeDTO=modelMapper.map(temp,LikeDTO.class);
                likeDTO.setPostId(temp.getPost().getId());
                likeDTO.setPostUsername(temp.getPost().getUser().getUsername());
                likeDTO.setLikeUserId(temp.getUser().getId());
                likeDTO.setLikeUsername(temp.getUser().getUsername());
                likeDTOList.add(likeDTO);
            }
            return likeDTOList;
        }
        return null;
    }
    public LikeDTO saveLike(LikeDTO likeDTO){
        Optional<Post> optionalPost=postRepository.findById(likeDTO.getPostId());
        Optional<User> optionalUser=userRepository.findById(likeDTO.getLikeUserId());
        if(optionalPost.isPresent() && optionalUser.isPresent()){
            Like like=modelMapper.map(likeDTO,Like.class);
            like.setPost(optionalPost.get());
            like.setUser(optionalUser.get());
            likeRepository.save(like);
            return likeDTO;
        }
        return null;
    }
    public Boolean deleteLike(Long id){
        Optional<Like> optionalLike=likeRepository.findById(id);
        if(optionalLike.isPresent()){
            Like like=optionalLike.get();
            likeRepository.delete(like);
            return true;
        }
        return false;
    }

}
