package com.projet.classwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ClassworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassworkApplication.class, args);
	}

}

