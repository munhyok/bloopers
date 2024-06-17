package com.grit.bloopers.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder
public class FollowUserDTO {
    private int userId;
    private int followerId;
}
