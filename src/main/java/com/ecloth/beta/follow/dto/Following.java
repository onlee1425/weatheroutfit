package com.ecloth.beta.follow.dto;

import com.ecloth.beta.follow.entity.Follow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class Following {

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Request {

        private Long targetId;
        @Builder.Default
        private boolean followStatus = true;
        public Follow toEntity(Long requesterId) {
            return Follow.builder()
                    .requesterId(requesterId)
                    .targetId(this.targetId)
                    .followStatus(this.followStatus)
                    .build();
        }
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Response {

        private Long targetId;
        private boolean followStatus;

        public static Response fromEntity(Follow follow) {
            return Response.builder()
                    .targetId(follow.getTargetId())
                    .followStatus(follow.isFollowStatus())
                    .build();
        }
    }

}
