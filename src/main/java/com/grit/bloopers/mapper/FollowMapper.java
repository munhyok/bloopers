package com.grit.bloopers.mapper;

import com.grit.bloopers.dto.FollowUserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {
    void followUser (FollowUserDTO followUserDTO);

    void unfollowUser (FollowUserDTO followUserDTO);

    List<Integer> getFollowList (Integer followerId);
}
