package com.example.employee_management;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employees =
            new CopyOnWriteArrayList<>();

    public EmployeeController() {
        employees.add(new Employee(1, "John", "QA"));
        employees.add(new Employee(2, "Rahul", "DevOps"));
        employees.add(new Employee(3, "Priya", "Development"));
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employees;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }
}
