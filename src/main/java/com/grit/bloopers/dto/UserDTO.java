package com.grit.bloopers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class UserDTO {

    private int id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    private String profileImage;
}
