package com.grit.bloopers.service;


import com.grit.bloopers.dto.UserDTO;
import com.grit.bloopers.mapper.UserMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(){}

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void createUser(UserDTO userDTO){

        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        userMapper.createUser(userDTO);
    }

    public void deleteUser(int id){
        userMapper.deleteUser(id);

    }

    public UserDTO getUserByEmail(String email, String rawPassword){
        UserDTO userDTO = userMapper.getUserByEmail(email);

        if (bCryptPasswordEncoder.matches(rawPassword, userDTO.getPassword())) {
            return userDTO;
        }
        return null;
    }
}
