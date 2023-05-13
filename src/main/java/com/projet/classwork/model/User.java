package com.projet.classwork.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@MappedSuperclass
public class User {
   
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @Column(nullable = false, unique = true)
    protected String registrationNumber;

    @Column(nullable = false, length = 80)
    protected String firstName;

    @Column(nullable = false , length = 80)
    protected String lastName;

    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false)
    protected String password;

    public String getRole() {
        return this.getClass().getSimpleName();
    }
}
