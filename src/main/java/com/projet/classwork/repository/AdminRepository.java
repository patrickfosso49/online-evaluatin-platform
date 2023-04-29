package com.projet.classwork.repository;

import com.projet.classwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
