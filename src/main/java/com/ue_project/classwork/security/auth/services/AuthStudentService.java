package com.ue_project.classwork.security.auth.services;

import com.ue_project.classwork.model.CustomUserDetails;
import com.ue_project.classwork.model.Student;
import com.ue_project.classwork.model.Teacher;
import com.ue_project.classwork.model.User;
import com.ue_project.classwork.repository.StudentRepository;
import com.ue_project.classwork.repository.TeacherRepository;
import com.ue_project.classwork.security.auth.AuthenticationRequest;
import com.ue_project.classwork.security.auth.AuthenticationResponse;
import com.ue_project.classwork.security.auth.RegisterRequest;
import com.ue_project.classwork.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthStudentService {


    private final StudentRepository studentRepo;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;


    public AuthenticationResponse register(RegisterRequest request) {

        var user =  Student.builder().firstName(request.getFirstname()).lastName(request.getLastname())
                .email(request.getEmail()).registrationNumber(request.getRegistrationNumber()) .password(passwordEncoder.encode(request.getPassword())).Role("Student")
                .build();

        studentRepo.save(user);
        var jwtToken = jwtService.generateTokenWithUserDetails(new CustomUserDetails(user));
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if (studentRepo == null) {
            throw new RuntimeException("userRepository is null");

        } else {
            if (request.getEmail() == null || request.getPassword() == null) {
                throw new IllegalArgumentException("Email and password cannot be null");
            }
            System.out.println("-----------------------log 1");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            System.out.println("--------------------------log 2");
            Optional<User> optionalUser = studentRepo.findByEmail(request.getEmail());
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
