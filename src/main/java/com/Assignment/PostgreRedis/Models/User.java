package com.Assignment.PostgreRedis.Models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    private List <Comment> comments;

    private String userName;

    private boolean isPremium;

}
