package com.Assignment.PostgreRedis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment.PostgreRedis.DTO.PostRequest;
import com.Assignment.PostgreRedis.Models.Post;
import com.Assignment.PostgreRedis.Services.AppService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class PostController {
    
    @Autowired
    private AppService appService;


    //create a Post
    @PostMapping("/createPost")
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest){
        Post post =appService.createPost(postRequest);
        return new ResponseEntity<>(post,HttpStatus.CREATED);
    }
}
