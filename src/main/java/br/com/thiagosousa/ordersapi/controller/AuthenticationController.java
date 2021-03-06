package br.com.thiagosousa.ordersapi.controller;

import br.com.thiagosousa.ordersapi.config.security.JwtTokenProvider;
import br.com.thiagosousa.ordersapi.controller.dto.JwtAuthenticationResponse;
import br.com.thiagosousa.ordersapi.controller.dto.LoginRequest;
import br.com.thiagosousa.ordersapi.service.AuthenticationService;
import br.com.thiagosousa.ordersapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = {"authentication"})
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationService service, JwtTokenProvider tokenProvider, UserService userService) {
        this.service = service;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @ApiOperation(value = "Authenticate an user")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        var jwt = service.authenticateUser(loginRequest);
        var userId = tokenProvider.getUserIdFromJWT(jwt);
        var user = userService.findById(userId);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, user));
    }

}
