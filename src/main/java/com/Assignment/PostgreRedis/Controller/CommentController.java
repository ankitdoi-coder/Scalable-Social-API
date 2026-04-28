package com.Assignment.PostgreRedis.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment.PostgreRedis.DTO.CommentRequest;
import com.Assignment.PostgreRedis.Models.Comment;
import com.Assignment.PostgreRedis.Services.AppService;




@RestController
@RequestMapping("/api")
public class CommentController {
    
    @Autowired
    private AppService appService;


    @PostMapping("/posts/{postId}/comment")
    public ResponseEntity<Comment> postComment(@PathVariable Long postId,@RequestBody CommentRequest commentRequest){
        Comment comment =appService.postCommment(postId,commentRequest);
        return new ResponseEntity<>(comment,HttpStatus.CREATED);
    }
}
