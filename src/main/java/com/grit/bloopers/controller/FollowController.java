package com.grit.bloopers.controller;



import com.grit.bloopers.dto.FollowUserDTO;
import com.grit.bloopers.service.FollowService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/users/{user_id}/follow")
    public ResponseEntity<FollowUserDTO> followUser(@RequestBody FollowUserDTO followUserDTO
    , @PathVariable int user_id) {
        followUserDTO.setFollowing_id(user_id);
        followService.followUser(followUserDTO);
        return new ResponseEntity<>(followUserDTO, HttpStatus.CREATED);
    }

    @PostMapping("/users/{user_id}/unfollow")
    public ResponseEntity<FollowUserDTO> unfollowUser(@RequestBody FollowUserDTO followUserDTO,
                                                      @PathVariable int user_id) {
        followUserDTO.setFollowing_id(user_id);
        followService.unfollowUser(followUserDTO);
        return new ResponseEntity<>(followUserDTO, HttpStatus.OK);
    }

    @GetMapping("/users/follower")
    public List<Integer> getFollowList() {
        return followService.getFollowList();


    }
}
