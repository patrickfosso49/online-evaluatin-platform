package com.projet.classwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.projet.classwork.model.User;

@NoRepositoryBean
public interface UserRepository< T extends User> extends JpaRepository<T, Long> {

    T findByEmail(String email);

     boolean existsByEmail(String email);

     boolean existsByRegistrationNumber(String registrationNumber);
}
