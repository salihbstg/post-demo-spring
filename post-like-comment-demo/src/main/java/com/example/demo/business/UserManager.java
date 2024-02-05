package com.example.demo.business;

import com.example.demo.dto.user.UserDTO;
import com.example.demo.entities.User;

import com.example.demo.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserManager {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<UserDTO> findAll(){
        List<User> userList=userRepository.findAll();
        List<UserDTO> userDTOList=new ArrayList<>();
        for(User temp:userList){
            userDTOList.add(modelMapper.map(temp,UserDTO.class));
        }
        return userDTOList;
    }
    public UserDTO findByUsernameOrId(String username){
        if(username.matches("-?\\d+(\\.\\d+)?")){
            Optional<User> optionalUser=userRepository.findById(Long.parseLong(username));
            return optionalUser.map(user -> modelMapper.map(user, UserDTO.class)).orElse(null);
        }
        Optional<User> optionalUser=userRepository.findByUsername(username);
        return optionalUser.map(user -> modelMapper.map(user, UserDTO.class)).orElse(null);
    }
    public UserDTO findById(Long id){
        Optional<User> optionalUser=userRepository.findById(id);
        return optionalUser.map(user -> modelMapper.map(user, UserDTO.class)).orElse(null);
    }
    public UserDTO saveUser(UserDTO user){
        User newUser=userRepository.save(modelMapper.map(user,User.class));
        return modelMapper.map(newUser,UserDTO.class);
    }
    public boolean deleteUser(Long id){
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }

}
