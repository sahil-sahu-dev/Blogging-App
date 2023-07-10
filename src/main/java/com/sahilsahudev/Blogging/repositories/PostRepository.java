package com.sahilsahudev.Blogging.repositories;

import com.sahilsahudev.Blogging.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(Integer userId);
}
