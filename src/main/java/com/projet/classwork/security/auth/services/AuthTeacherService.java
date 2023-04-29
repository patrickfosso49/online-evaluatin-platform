package com.projet.classwork.security.auth.services;

import java.util.Optional;


import com.projet.classwork.repository.TeacherRepository;
import com.projet.classwork.security.auth.AuthenticationResponse;
import com.projet.classwork.security.config.JwtService;
import com.projet.classwork.model.CustomUserDetails;
import com.projet.classwork.model.Teacher;
import com.projet.classwork.model.User;
import com.projet.classwork.security.auth.AuthenticationRequest;
import com.projet.classwork.security.auth.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthTeacherService {


    //private final UserRepository userRepository;

    private final TeacherRepository teacherRepo;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;


    public AuthenticationResponse register(RegisterRequest request) {

        var user =  Teacher.builder().firstName(request.getFirstname()).lastName(request.getLastname())
                .email(request.getEmail()).registrationNumber(request.getRegistrationNumber()) .password(passwordEncoder.encode(request.getPassword())).Role("Teacher")
                .build();

        teacherRepo.save(user);
        var jwtToken = jwtService.generateTokenWithUserDetails(new CustomUserDetails(user));
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if (teacherRepo == null) {
            throw new RuntimeException("userRepository is null");

        } else {
            if (request.getEmail() == null || request.getPassword() == null) {
                throw new IllegalArgumentException("Email and password cannot be null");
            }
            System.out.println("-----------------------log 1");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            System.out.println("--------------------------log 2");
            Optional<User> optionalUser = teacherRepo.findByEmail(request.getEmail());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                var jwtToken = jwtService.generateTokenWithUserDetails(new CustomUserDetails(user));
                System.out.println(
                        "------------------Authenticated as a: " + request.getRole() + "-----------------------\n AND email: "+request.getEmail());
                return AuthenticationResponse.builder().token(jwtToken).build();
            } else {
                // handle user not found case
                System.out.println("User not found");
                return null;
            }
        }
    }

}