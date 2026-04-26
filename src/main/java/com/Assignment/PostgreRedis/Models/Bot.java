package com.Assignment.PostgreRedis.Models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "bots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bot {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String persona_description;

    @OneToMany(mappedBy="bot")
    private List<Comment> comments;
}
