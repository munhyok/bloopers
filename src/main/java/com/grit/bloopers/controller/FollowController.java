package com.grit.bloopers.controller;



import com.grit.bloopers.dto.FollowUserDTO;
import com.grit.bloopers.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
