package com.example.library.service.employee;

import com.example.library.model.entity.Employee;
import com.example.library.repository.EmployeeRepository;
import com.example.library.service.employee.security.UserDetailsImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    public boolean existsEmployeeByLogin(String login){
        return employeeRepository.existsEmployeeByLogin(login);
    }

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findEmployeeByLogin(username);
        return new UserDetailsImplementation(employee.getLogin(), employee.getPassword());
    }
}
