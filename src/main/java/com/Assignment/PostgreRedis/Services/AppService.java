package com.Assignment.PostgreRedis.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Assignment.PostgreRedis.Repositories.PostRepository;
import com.Assignment.PostgreRedis.Repositories.UserRepository;
import com.Assignment.PostgreRedis.Repositories.BotRepository;
import com.Assignment.PostgreRedis.Models.Post;
import com.Assignment.PostgreRedis.Models.Bot;
import com.Assignment.PostgreRedis.Models.User;
import com.Assignment.PostgreRedis.DTO.CommentRequest;
import com.Assignment.PostgreRedis.DTO.PostRequest;
import com.Assignment.PostgreRedis.Models.Comment;
import java.util.stream.Collectors;
import java.util.*;
import com.Assignment.PostgreRedis.Repositories.CommentRepository;


@Service
public class AppService {
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BotRepository botRepo;

    @Autowired
    private CommentRepository commentRepo;


    // Create Post
    public Post createPost(PostRequest postRequest) {
        Post post = new Post();

        // Normal Input Setting
        post.setContent(postRequest.getContent());

        // Bot Setting if
        Bot bot=null;
        if(postRequest.getBot()!=null && !postRequest.getBot().isEmpty()){
            bot = new Bot();
            bot.setName(postRequest.getBot());
            bot.setPersona_description(postRequest.getPersonaDescription());
            botRepo.save(bot);
            post.setBot(bot);
        }
       

        // User Setting
        User user = new User();
        user.setPremium(postRequest.isPremium());
        user.setUserName(postRequest.getUserName());
        userRepo.save(user);

        post.setUser(user);

        // Comment Setting
        if (postRequest.getComments() != null) {
            final Bot finalBot=bot;
            List<Comment> comments = postRequest.getComments().stream()
                    .map(commentText -> {
                        Comment comment = new Comment();
                        comment.setContent(commentText);
                        comment.setPost(post); // Maintain bidirectional relationship
                        comment.setBot(finalBot);
                        comment.setUser(user);
                        return comment;
                    })
                    .collect(Collectors.toList()); // You can use .toList() instead if you are on Java 16+
            
            post.setComments(comments);
        }

        postRepo.save(post);
        return post;
    }




    //Commment on a Post
    public Comment postCommment(Long postId,CommentRequest commentRequest){
        Comment newComment=new Comment();

        //Normal Entries
        // newComment.setId(commentRequest.getId());
        newComment.setContent(commentRequest.getContent());
        newComment.setDepthLevel(commentRequest.getDepthLevel());

        //Object entries
        newComment.setPost(postRepo.findById(commentRequest.getPostId())
        .orElseThrow(()-> new RuntimeException("Post not found")));

        if(commentRequest.getBotId() !=null ){
            newComment.setBot(botRepo.findById(commentRequest.getBotId())
            .orElseThrow(()-> new RuntimeException("Bot not found")));
        }

        newComment.setUser(userRepo.findById(commentRequest.getUserId())
        .orElseThrow(()-> new RuntimeException("User not found")));


        commentRepo.save(newComment);
        return newComment;
    }

    // Like a Post
    public Post likePost(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        int currentLikes = post.getLikesCount() != null ? post.getLikesCount() : 0;
        post.setLikesCount(currentLikes + 1);
        return postRepo.save(post);
    }
}