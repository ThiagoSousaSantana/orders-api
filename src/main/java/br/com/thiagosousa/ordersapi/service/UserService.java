package br.com.thiagosousa.ordersapi.service;

import br.com.thiagosousa.ordersapi.controller.dto.SignUpRequest;
import br.com.thiagosousa.ordersapi.controller.dto.UserResponse;
import br.com.thiagosousa.ordersapi.model.RoleName;
import br.com.thiagosousa.ordersapi.model.User;
import br.com.thiagosousa.ordersapi.repository.RoleRepository;
import br.com.thiagosousa.ordersapi.repository.UserRepository;
import br.com.thiagosousa.ordersapi.service.exception.AppException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        var user =  userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));
        return UserResponse.create(user);
    }

    @Transactional
    public UserResponse findById(Long id){
        var user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id:" + id));
        return UserResponse.create(user);
    }

    public User registerUser(SignUpRequest signUpRequest){
        if (userRepository.existsByEmail(signUpRequest.getEmail()))
            throw new IllegalArgumentException("Email Address is already taken!");

        if (userRepository.existsByUsername(signUpRequest.getUsername()))
            throw new IllegalArgumentException("Username is already taken!");

        var role = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        var user = new User(
                null,
                signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                Set.of(role));

        return userRepository.save(user);
    }

}
