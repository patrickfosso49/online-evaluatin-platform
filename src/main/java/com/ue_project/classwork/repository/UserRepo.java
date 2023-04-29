package com.ue_project.classwork.repository;

import com.ue_project.classwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}

