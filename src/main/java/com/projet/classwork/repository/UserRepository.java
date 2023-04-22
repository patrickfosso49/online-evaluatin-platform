package com.projet.classwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.projet.classwork.model.User;

@NoRepositoryBean
public interface UserRepository< T extends User> extends JpaRepository<User, Long> {

    public T findByEmail(String email);

    public boolean existsByEmail(String email);

    public boolean existsByRegistrationNumber(String registrationNumber);
}
