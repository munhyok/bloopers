package com.grit.bloopers.controller;


import com.grit.bloopers.dto.LoginDTO;
import com.grit.bloopers.dto.UserDTO;
import com.grit.bloopers.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }



    //DTO를 Request와 Response로 나눠서 처리

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login (@RequestBody LoginDTO loginDTO, HttpServletRequest request) {


        UserDTO userDTO = userService.getUserByEmail(loginDTO.getEmail(), loginDTO.getPassword());
        if (userDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", userDTO);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
