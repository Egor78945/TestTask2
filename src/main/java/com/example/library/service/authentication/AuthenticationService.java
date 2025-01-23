package com.example.library.service.authentication;

import com.example.library.configuration.security.jwt.JWTConfiguration;
import com.example.library.model.dto.AuthenticationDTO;
import com.example.library.model.entity.Employee;
import com.example.library.service.employee.EmployeeService;
import com.example.library.service.employee.validation.EmployeeValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTConfiguration jwtConfiguration;

    public String authenticate(AuthenticationDTO authenticationDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtConfiguration.generateToken(authentication);
    }

    public void register(AuthenticationDTO authenticationDTO){
        if(EmployeeValidation.isValid(authenticationDTO) && !employeeService.existsEmployeeByLogin(authenticationDTO.login())){
            employeeService.saveEmployee(new Employee(authenticationDTO.login(), passwordEncoder.encode(authenticationDTO.password())));
        } else {
            throw new RuntimeException("Wrong employee details or the login is busy. Login and password must to contains only digits and have length from 5 to 30 symbols.");
        }
    }
}
