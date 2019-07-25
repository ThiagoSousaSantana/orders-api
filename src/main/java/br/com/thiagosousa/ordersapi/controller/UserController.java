package br.com.thiagosousa.ordersapi.controller;

import br.com.thiagosousa.ordersapi.controller.dto.ApiResponse;
import br.com.thiagosousa.ordersapi.controller.dto.SignUpRequest;
import br.com.thiagosousa.ordersapi.controller.dto.UserResponse;
import br.com.thiagosousa.ordersapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Api(tags = { "users" })
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Register an user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        var result = service.registerUser(signUpRequest);

        var uri = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(uri).body(new ApiResponse(true, "User registered successfully"));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a user by ID")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
}

