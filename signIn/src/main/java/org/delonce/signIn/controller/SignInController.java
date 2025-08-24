package org.delonce.signIn.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delonce.signIn.dto.SignInRequest;
import org.delonce.signIn.dto.SignInResponse;
import org.delonce.signIn.model.User;
import org.delonce.signIn.service.impl.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signIn")
@RequiredArgsConstructor
public class SignInController {

    @Autowired
    private SignInService signInService;

    @CachePut(value = "users", key = "#signInRequest.login")
    @PostMapping
    public ResponseEntity<SignInResponse> createUser(@Valid @RequestBody SignInRequest signInRequest) {
        User newUser = signInRequest.createInstance();
        try {
            return ResponseEntity.ok(new SignInResponse(
                    signInService.create(newUser)
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new SignInResponse(
                    newUser,
                    e.getMessage()
            ));
        }
    }
}
