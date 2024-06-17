package com.grit.bloopers.service;

import com.grit.bloopers.dto.FollowUserDTO;
import com.grit.bloopers.exception.error.LoginRequiredException;
import com.grit.bloopers.mapper.FollowMapper;
import com.grit.bloopers.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        followMapper.followUser(followUserDTO);
    }
}
