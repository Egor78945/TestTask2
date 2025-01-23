package com.example.library.controller.authentication;

import com.example.library.controller.authentication.advice.AuthenticationControllerExceptionHandler;
import com.example.library.model.dto.AuthenticationDTO;
import com.example.library.service.authentication.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/auth")
@RequiredArgsConstructor
@AuthenticationControllerExceptionHandler
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    @Operation(description = "Регистрирует нового сотрудника.")
    public ResponseEntity<String> registration(@RequestBody AuthenticationDTO authenticationDTO){
        authenticationService.register(authenticationDTO);
        return ResponseEntity.ok("You have been registered.");
    }

    @GetMapping
    @Operation(description = "Аутентифицирует сотрудника и возвращает JWT.")
    public ResponseEntity<String> login(@RequestBody AuthenticationDTO authenticationDTO){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationDTO));
    }
}
