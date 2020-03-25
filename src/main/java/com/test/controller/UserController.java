package com.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.JwtResponse;
import com.test.model.ResponseMessage;
import com.test.model.Role;
import com.test.model.Users;
import com.test.security.JwtProvider;
import com.test.service.UserService;

@RestController
@RequestMapping("api/auth")
public class UserController {
@Autowired UserService userService;

@Autowired AuthenticationManager authenticationManager;

@Autowired JwtProvider jwtProvider;

@Autowired PasswordEncoder passwordEncoder;

@PostMapping("signup")
public ResponseEntity<?> saveUser(@Valid @RequestBody Users user){
		System.out.println("In controller");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(userService.existByEmail(user.getUserEmail())){
			return new ResponseEntity<>(new ResponseMessage("email already exists",400),HttpStatus.CREATED);
		}
		if(userService.existByUsername(user.getUserName())){
			return new ResponseEntity<>(new ResponseMessage("username already exists",400),HttpStatus.CREATED);
		}
	userService.save(user);
	return new ResponseEntity<>(new ResponseMessage("user registration succesful",201),HttpStatus.CREATED);
}

@PostMapping("signin")
public ResponseEntity<?> auth(@RequestBody Users user){
	Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
	SecurityContextHolder.getContext().setAuthentication(authentication);
	String jwtToken=jwtProvider.generateToken(authentication);
	UserDetails userDetails=(UserDetails)authentication.getPrincipal();
	return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getUsername(), userDetails.getAuthorities()));
	

}

@GetMapping("roles")
public List<Role> getAllRoles(){
	return userService.getAllRoles();
}

}
