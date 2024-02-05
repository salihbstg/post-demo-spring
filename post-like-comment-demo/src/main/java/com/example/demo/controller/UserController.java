package com.example.demo.controller;

import com.example.demo.business.UserManager;
import com.example.demo.dto.user.UserDTO;
import com.example.demo.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserManager userManager;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return new ResponseEntity<>(userManager.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{user}")
    public ResponseEntity<UserDTO> findByUsernameOrId(@PathVariable String user){
        UserDTO userDTO=userManager.findByUsernameOrId(user);
        if(userDTO==null){
            throw new ApplicationException("User not found!");
        }
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        return new ResponseEntity<>(userManager.saveUser(user),HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<UserDTO> putUser(@RequestBody UserDTO user){
        if(user.getId()!=null){
            return new ResponseEntity<>(userManager.saveUser(user),HttpStatus.OK);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userManager.deleteUser(id),HttpStatus.OK);
    }
}
