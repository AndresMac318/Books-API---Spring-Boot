
package com.company.books.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.request.AuthRequest;
import com.company.books.backend.response.TokenResponse;
import com.company.books.backend.service.JWTService;

@RestController
@RequestMapping("/v1")
public class TokenController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTService jwtService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthRequest request){
		
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword())
		);
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUser());
		
		final String jwt = jwtService.generateToken(userDetails);
		
		return ResponseEntity.ok(new TokenResponse(jwt));
	}
	
}
