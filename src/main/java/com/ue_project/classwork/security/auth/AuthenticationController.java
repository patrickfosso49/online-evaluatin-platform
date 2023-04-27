package com.ue_project.classwork.security.auth;

import com.ue_project.classwork.security.config.AuthControllerHelper;
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
@RequestMapping("/api")
public class AuthenticationController {


	private final AuthenticationService service;

	// -----------------Teacher--------------------


	@PostMapping("/reg")
	public ResponseEntity<AuthenticationResponse> register(
		@RequestBody RegisterRequest request
	){
		System.out.println("Hey in register");
		return ResponseEntity.ok(service.register(request));
	}

	@PostMapping(	"/auth")
	public ResponseEntity<AuthenticationResponse> authenticate(
		@RequestBody AuthenticationRequest request
	){
		System.out.println("Hey in login");
		return ResponseEntity.ok(service.authenticate(request));
	}



//
//	// -----------------Admin--------------------
//	@PostMapping(AuthControllerHelper.REGISTER_MAPPING_PATH_TEACHER)
//	public ResponseEntity<AuthenticationResponse> registerAdmin(
//		@RequestBody RegisterRequest request
//	){
//		return ResponseEntity.ok(service.register(request));
//	}
//
//	@PostMapping(AuthControllerHelper.AUTH_MAPPING_PATH_TEACHER)
//	public ResponseEntity<AuthenticationResponse> authenticateAdmin(
//		@RequestBody AuthenticationRequest request
//	){
//		return ResponseEntity.ok(service.authenticate(request));
//	}
//
//
//
//	// -----------------Student--------------------
//		@PostMapping(AuthControllerHelper.REGISTER_MAPPING_PATH_TEACHER)
//		public ResponseEntity<AuthenticationResponse> registerStudent(
//			@RequestBody RegisterRequest request
//		){
//			return ResponseEntity.ok(service.register(request));
//		}
//
//		@PostMapping(AuthControllerHelper.AUTH_MAPPING_PATH_TEACHER)
//		public ResponseEntity<AuthenticationResponse> authenticateStuden(
//			@RequestBody AuthenticationRequest request
//		){
//			return ResponseEntity.ok(service.authenticate(request));
//		}
//

}
