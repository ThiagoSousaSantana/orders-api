package br.com.thiagosousa.ordersapi.controller;

import br.com.thiagosousa.ordersapi.controller.dto.ApiResponse;
import br.com.thiagosousa.ordersapi.controller.dto.JwtAuthenticationResponse;
import br.com.thiagosousa.ordersapi.controller.dto.LoginRequest;
import br.com.thiagosousa.ordersapi.controller.dto.SignUpRequest;
import br.com.thiagosousa.ordersapi.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@Api(tags = {"authentication"})
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @ApiOperation(value = "Authenticate an user")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        var jwt = service.authenticateUser(loginRequest);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @ApiOperation(value = "Register an user")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        var result = service.registerUser(signUpRequest);

        var uri = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(uri).body(new ApiResponse(true, "User registered successfully"));
    }



}
