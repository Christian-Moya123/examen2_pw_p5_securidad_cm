package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JwtUtils;
import com.example.demo.service.to.UsuarioTO;

@RestController
@RequestMapping("/tokens")
@CrossOrigin(origins = "http://localhost:8080") 
public class TokenControllerRestFul {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/obtener")
	public String obtenerToken(@RequestBody UsuarioTO usuario) {
		
		System.out.println(usuario.getPassword());
		System.out.println(usuario.getSemilla());
		System.out.println(usuario.getUsername());
		System.out.println(usuario.getTiempo());
		
		this.authenticated(usuario.getUsername(), usuario.getPassword());

	return	this.jwtUtils.generateToken(usuario.getUsername(),usuario.getTiempo(), usuario.getSemilla());
				
		
		}
	
	//Autenticar un usuario
	private void authenticated(String usuario, String password) {
		Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
