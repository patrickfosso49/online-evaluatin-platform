package com.projet.classwork.repository;

import java.util.Optional;

import com.projet.classwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

	public interface TeacherRepository extends JpaRepository<User, Integer> {

		 Optional<User> findByEmail(String email);
	}