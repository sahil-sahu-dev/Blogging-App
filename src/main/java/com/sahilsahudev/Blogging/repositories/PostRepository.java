package com.sahilsahudev.Blogging.repositories;

import com.sahilsahudev.Blogging.models.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(Integer userId);
    @Query("SELECT p FROM Post p JOIN p.user u JOIN u.following f WHERE f.id = :userId ORDER BY p.dateCreated DESC")
    List<Post> findPostsByFollowingOrderByDateCreatedDesc(Integer userId);
}
