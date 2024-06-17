package com.grit.bloopers.mapper;

import com.grit.bloopers.dto.FollowUserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowMapper {
    void followUser (FollowUserDTO followUserDTO);

    void unfollowUser (FollowUserDTO followUserDTO);
}
