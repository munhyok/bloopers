package com.grit.bloopers.service;

import com.grit.bloopers.dto.FollowUserDTO;
import com.grit.bloopers.exception.error.LoginRequiredException;
import com.grit.bloopers.mapper.FollowMapper;
import com.grit.bloopers.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowMapper followMapper;

    public void followUser(FollowUserDTO followUserDTO) {
        int sessionId = SessionUtil.getLoginUserId();

        //로그인 하지 않았으면 Exception
        if (sessionId == -1) {
            throw new LoginRequiredException();
        }

        followUserDTO.setUser_id(sessionId);

        followMapper.followUser(followUserDTO);
    }

    public void unfollowUser(FollowUserDTO followUserDTO) {
        int sessionId = SessionUtil.getLoginUserId();
        if (sessionId == -1) {
            throw new LoginRequiredException();
        }

        followUserDTO.setUser_id(sessionId);
        followMapper.unfollowUser(followUserDTO);


    }

    public List<Integer> getFollowList() {
        int sessionId = SessionUtil.getLoginUserId();
        if (sessionId == -1) {
            throw new  LoginRequiredException();
        }
        return followMapper.getFollowList(sessionId);
    }
}
