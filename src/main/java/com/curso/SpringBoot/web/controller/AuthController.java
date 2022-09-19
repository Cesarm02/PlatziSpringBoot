package com.curso.SpringBoot.web.controller;

import com.curso.SpringBoot.domain.dto.AuthenticationRequest;
import com.curso.SpringBoot.domain.dto.AuthenticationResponse;
import com.curso.SpringBoot.domain.service.PlaztiUserDetailsService;
import com.curso.SpringBoot.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PlaztiUserDetailsService plaztiUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest authenticationRequest){

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUser(),
                    authenticationRequest.getPassword()));
            UserDetails userDetails = plaztiUserDetailsService.loadUserByUsername(authenticationRequest.getUser());
            String jwt =  jwtUtil.generateToken(userDetails);
            return  new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);

        }catch (BadCredentialsException ex){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
