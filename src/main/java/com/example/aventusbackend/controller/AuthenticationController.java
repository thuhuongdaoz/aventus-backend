package com.example.aventusbackend.controller;

import com.example.aventusbackend.dto.request.AuthenticationRequest;
import com.example.aventusbackend.dto.request.IntrospectRequest;
import com.example.aventusbackend.dto.response.AuthenticationResponse;
import com.example.aventusbackend.dto.response.IntrospectResponse;
import com.example.aventusbackend.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/login")
    AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return result;
    }

    @PostMapping("/introspect")
    IntrospectResponse authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return result;
    }
}
