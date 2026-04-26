package com.Assignment.PostgreRedis.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Assignment.PostgreRedis.Repositories.PostRepository;
import com.Assignment.PostgreRedis.Repositories.UserRepository;
import com.Assignment.PostgreRedis.Repositories.BotRepository;
import com.Assignment.PostgreRedis.Repositories.CommentRepository;
import com.Assignment.PostgreRedis.Models.Post;
import com.Assignment.PostgreRedis.Models.Bot;
import com.Assignment.PostgreRedis.Models.User;
import com.Assignment.PostgreRedis.DTO.PostRequest;
import com.Assignment.PostgreRedis.Models.Comment;
import java.util.stream.Collectors;
import java.util.*;



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
    


    //Create Post 
    public Post createPost(PostRequest postRequest) {
        Post post = new Post();

        // Normal Input Setting
        post.setContent(postRequest.getContent());

        //Bot Setting if 
        Bot bot = new Bot();
        bot.setName(postRequest.getBot());

        botRepo.save(bot);
        post.setBot(bot);


        //User Setting 
        User user=new User();
        user.setPremium(postRequest.isPremium());
        user.setUserName(postRequest.getUserName());
        userRepo.save(user);

        post.setUser(user);
        


        //Comment Setting
        if (postRequest.getComments() != null) {
            List<Comment> comments = postRequest.getComments().stream()
                .map(commentText -> {
                    Comment comment = new Comment();
                    comment.setContent(commentText);
                    comment.setPost(post); // Maintain bidirectional relationship
                    comment.setBot(bot);
                    comment.setUser(user);
                    return comment;
                })
                .collect(Collectors.toList()); // You can use .toList() instead if you are on Java 16+
            commentRepo.saveAll(comments);
            post.setComments(comments);
        }

        return postRepo.save(post);

    }
}