package com.ue_project.classwork.repository;

import java.util.Optional;

import com.ue_project.classwork.model.Teacher;
import com.ue_project.classwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



	public interface TeacherRepository extends JpaRepository<User, Integer> {

	 Optional<User> findByEmail(String email);
	}