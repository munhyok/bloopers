package com.grit.bloopers.mapper;

import com.grit.bloopers.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void createUser(UserDTO userDTO);

    void deleteUser(int id);

    UserDTO getUserByEmail(String email);



}
