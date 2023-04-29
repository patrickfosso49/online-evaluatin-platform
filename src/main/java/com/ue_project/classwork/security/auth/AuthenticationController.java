package com.ue_project.classwork.security.auth;

import com.ue_project.classwork.security.auth.services.AuthAdminService;
import com.ue_project.classwork.security.auth.services.AuthStudentService;
import com.ue_project.classwork.security.auth.services.AuthTeacherService;
import com.ue_project.classwork.security.config.AuthHelper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@ComponentScan
@RestController
@RequiredArgsConstructor
@RequestMapping(AuthHelper.REQUEST_MAPPING_PATH)
public class AuthenticationController {


	private final AuthAdminService AuthAdminService;
	private final AuthTeacherService AuthTeacherService;
	private final AuthStudentService AuthStudentService;

	// -----------------Admin--------------------


	@PostMapping(AuthHelper.REGISTER_MAPPING_PATH_ADMIN)
	public ResponseEntity<AuthenticationResponse> registerAdmin(
		@RequestBody RegisterRequest request
	){
		System.out.println("Hey in register");
		return ResponseEntity.ok(AuthAdminService.register(request));
	}

	@PostMapping(	AuthHelper.AUTH_MAPPING_PATH_ADMIN)
	public ResponseEntity<AuthenticationResponse> authenticateAdmin(
		@RequestBody AuthenticationRequest request
	){
		System.out.println("Hey in login");
		return ResponseEntity.ok(AuthAdminService.authenticate(request));
	}





	// -----------------Teacher--------------------
	@PostMapping(AuthHelper.REGISTER_MAPPING_PATH_TEACHER)
	public ResponseEntity<AuthenticationResponse> registerTeacher(
		@RequestBody RegisterRequest request
	){
		return ResponseEntity.ok(AuthTeacherService.register(request));
	}

	@PostMapping(AuthHelper.AUTH_MAPPING_PATH_TEACHER)
	public ResponseEntity<AuthenticationResponse> authenticateTeacher(
		@RequestBody AuthenticationRequest request
	){
		return ResponseEntity.ok(AuthTeacherService.authenticate(request));
	}



	// -----------------Student--------------------
		@PostMapping(AuthHelper.REGISTER_MAPPING_PATH_STUDENT)
		public ResponseEntity<AuthenticationResponse> registerStudent(
			@RequestBody RegisterRequest request
		){
			return ResponseEntity.ok(AuthStudentService.register(request));
		}

		@PostMapping(AuthHelper.AUTH_MAPPING_PATH_PATH_STUDENT)
		public ResponseEntity<AuthenticationResponse> authenticateStudent(
			@RequestBody AuthenticationRequest request
		){
			return ResponseEntity.ok(AuthStudentService.authenticate(request));
		}



}
