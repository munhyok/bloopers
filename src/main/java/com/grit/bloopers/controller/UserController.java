package com.grit.bloopers.controller;


import com.grit.bloopers.controller.handler.CommonController;
import com.grit.bloopers.dto.LoginDTO;
import com.grit.bloopers.dto.UserDTO;
import com.grit.bloopers.service.UserService;
import com.grit.bloopers.utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }




    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login (@RequestBody LoginDTO loginDTO, HttpSession session) {
        UserDTO userDTO = userService.getUserByEmail(loginDTO.getEmail(), loginDTO.getPassword(), session);

    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> logout (HttpSession session) {
        SessionUtil.logoutUser(session);
        return new ResponseEntity<>("로그아웃 완료", HttpStatus.OK);

    }



}
