package br.com.thiagosousa.ordersapi.service;

import br.com.thiagosousa.ordersapi.config.security.JwtTokenProvider;
import br.com.thiagosousa.ordersapi.controller.dto.LoginRequest;
import br.com.thiagosousa.ordersapi.controller.dto.SignUpRequest;
import br.com.thiagosousa.ordersapi.model.RoleName;
import br.com.thiagosousa.ordersapi.model.User;
import br.com.thiagosousa.ordersapi.repository.RoleRepository;
import br.com.thiagosousa.ordersapi.repository.UserRepository;
import br.com.thiagosousa.ordersapi.service.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public User registerUser(SignUpRequest signUpRequest){

        if (userRepository.existsByEmail(signUpRequest.getEmail()))
            throw new IllegalArgumentException("Email Address is already taken!");

        if (userRepository.existsByUsername(signUpRequest.getUsername()))
            throw new IllegalArgumentException("Username is already taken!");

        var user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        var role = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(role));

        return userRepository.save(user);
    }

    public String authenticateUser(LoginRequest loginRequest){
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken(authentication);
    }
}
