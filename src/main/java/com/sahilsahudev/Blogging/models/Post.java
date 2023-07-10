package com.sahilsahudev.Blogging.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_post")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private int id;
    private String title;
    @Lob
    private String body;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes;

}
