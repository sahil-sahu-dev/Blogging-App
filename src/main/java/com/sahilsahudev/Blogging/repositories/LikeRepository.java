package com.sahilsahudev.Blogging.repositories;

import com.sahilsahudev.Blogging.models.Dto.LikeDto;
import com.sahilsahudev.Blogging.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    List<Like> findAllByPostId(Integer postId);
}
