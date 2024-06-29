package com.app.project.controller;


import com.app.project.service.dto.JwtRequest;
import com.app.project.service.dto.JwtResponse;
import com.app.project.application.cofig.JwtUtil;
import com.app.project.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
     private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;



    @RequestMapping(value="/token",method = RequestMethod.POST)
    public ResponseEntity<?> getToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        System.out.println(jwtRequest);

        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

        }
        catch(UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        catch(BadCredentialsException e) {
            e.printStackTrace();
            System.out.println("Bad Credentials");
        }

        // good area...
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        System.out.println("JWT token"+token);
        return ResponseEntity.ok(new JwtResponse(token));




    }

}
