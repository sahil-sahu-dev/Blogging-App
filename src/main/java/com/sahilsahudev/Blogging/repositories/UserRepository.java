package com.sahilsahudev.Blogging.repositories;

import com.sahilsahudev.Blogging.models.Post;
import com.sahilsahudev.Blogging.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);
}
