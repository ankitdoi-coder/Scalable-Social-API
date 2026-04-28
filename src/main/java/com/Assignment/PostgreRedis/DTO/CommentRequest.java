package com.Assignment.PostgreRedis.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    // private Long id;
    private Long postId;
    private Long botId;
    private Long userId;
    private String content;
    private int depthLevel;
}
