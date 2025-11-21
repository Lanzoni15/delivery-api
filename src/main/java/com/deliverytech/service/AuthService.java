package com.deliverytech.service;

import com.deliverytech.dto.LoginRequest;
import com.deliverytech.dto.LoginResponse;
import com.deliverytech.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getSenha())
        );

        var user = userDetailsService.loadUserByUsername(req.getEmail());
        String token = jwtUtil.generateToken(user);

        return new LoginResponse(token);
    }
}
