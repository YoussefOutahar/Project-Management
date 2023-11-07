package com.yorastd.projectmanagement.Repositories;

import com.yorastd.projectmanagement.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}