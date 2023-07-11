package com.sahilsahudev.Blogging.repositories;

import com.sahilsahudev.Blogging.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByPostId(Integer postId);
}
