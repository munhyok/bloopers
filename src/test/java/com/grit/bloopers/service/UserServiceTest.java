package com.grit.bloopers.service;


import com.grit.bloopers.dto.LoginDTO;
import com.grit.bloopers.dto.UserDTO;
import com.grit.bloopers.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Mock
    UserMapper userMapper;

    UserDTO userDTO;
    LoginDTO loginDTO;



    @BeforeEach
    public void setUp() {
        userDTO = UserDTO.builder()
                .id(1)
                .email("test@email.com")
                .password(bCryptPasswordEncoder.encode("password"))
                .phoneNumber("01011112222")
                .profileImage("")
                .name("test user")
                .build();

        loginDTO = LoginDTO.builder()
                .email("test@email.com")
                .password("password")
                .build();
    }






    @Test
    @DisplayName("회원가입 테스트")
    public void createUserTest() {
        doNothing().when(userMapper).createUser(any(UserDTO.class));
        userService.createUser(userDTO);
        verify(userMapper).createUser(any(UserDTO.class));
    }


}
