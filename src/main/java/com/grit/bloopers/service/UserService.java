package com.grit.bloopers.service;


import com.grit.bloopers.dto.UserDTO;
import com.grit.bloopers.exception.common.LoginSuccessException;
import com.grit.bloopers.exception.error.LoginFailureException;
import com.grit.bloopers.mapper.UserMapper;
import com.grit.bloopers.utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    public void createUser(UserDTO userDTO){

        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        userMapper.createUser(userDTO);
    }

    public void deleteUser(int id){
        userMapper.deleteUser(id);

    }



    public UserDTO getUserByEmail(String email, String rawPassword, HttpSession session){
        UserDTO userDTO = userMapper.getUserByEmail(email);

        if (userDTO == null || !bCryptPasswordEncoder.matches(rawPassword, userDTO.getPassword())){
            throw new LoginFailureException();
        }

        SessionUtil.setLoginUsersId(session, userDTO.getId());



        throw new LoginSuccessException();
    }



}




