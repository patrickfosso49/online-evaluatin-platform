package com.projet.classwork.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projet.classwork.model.ClassworkUserDetails;
import com.projet.classwork.model.User;
import com.projet.classwork.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentUserDetailsService implements UserDetailsService{
    
    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = studentRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException(username);
        
        return new ClassworkUserDetails(user);
    
    }


}
