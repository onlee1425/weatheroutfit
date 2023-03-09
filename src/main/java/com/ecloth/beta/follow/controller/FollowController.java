package com.ecloth.beta.follow.controller;

import com.ecloth.beta.follow.dto.FollowList;
import com.ecloth.beta.follow.dto.Following;
import com.ecloth.beta.follow.exception.FollowException;
import com.ecloth.beta.follow.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

import static com.ecloth.beta.follow.exception.ErrorCode.FOLLOW_REQUEST_NOT_VALID;
import static com.ecloth.beta.follow.exception.ErrorCode.UNFOLLOW_REQUEST_NOT_VALID;

@RestController
@RequestMapping("/api/member/follow")
public class FollowController {

    private FollowService followService;

    @PostMapping
    public ResponseEntity<Following.Response> follow(@ApiIgnore Principal principal,
                                    @RequestBody Following.Request request){

        if (request.isFollowStatus() == false) {
            throw new FollowException(FOLLOW_REQUEST_NOT_VALID);
        }

        String requesterEmail = principal.getName();
        Following.Response response = followService.followOrUnFollowTarget(requesterEmail, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Following.Response> getFollowStatus(@ApiIgnore Principal principal,
                                             @RequestParam Long targetId){

        Following.Response response = followService.getFollowStatus(principal.getName(), targetId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<FollowList.Response> getFollowList(@ApiIgnore Principal principal,
                                           FollowList.Request request){

        FollowList.Response response = followService.getFollowList(principal.getName(), request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Following.Response> unfollow(@ApiIgnore Principal principal,
                                      @RequestBody Following.Request request){

        if (request.isFollowStatus() == true) {
            throw new FollowException(UNFOLLOW_REQUEST_NOT_VALID);
        }

        String requesterEmail = principal.getName();
        Following.Response response = followService.followOrUnFollowTarget(requesterEmail, request);

        return ResponseEntity.ok(response);
    }

}
