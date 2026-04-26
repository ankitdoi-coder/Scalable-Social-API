package com.Assignment.PostgreRedis.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
    private String userName;
    private boolean isPremium;
    private String bot;

    private List<String> comments;
    
    private String content;
    
}