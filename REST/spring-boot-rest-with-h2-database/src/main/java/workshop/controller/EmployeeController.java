package workshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workshop.entity.Employee;
import workshop.exception.ResourceNotFoundException;
import workshop.repository.EmployeeRepository;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api")
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "employees")
    public List<Employee> getAll() {
        log.info("getAll() method start");
        return repository.findAll();
    }

    @GetMapping(value = "employee/{id}")
    public Employee getById(@PathVariable("id") String id) {
        log.info("getById() method start");
        return repository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("No employee found with id: " + id));
    }

    @GetMapping(value = "employees/{firstName}")
    public List<Employee> getByFirstName(@PathVariable("firstName") String firstName) {
        log.info("getByFirstName() method start");

        List<Employee> employeesByFirstName = repository.findByFirstName(firstName);
        if (employeesByFirstName.isEmpty()) {
            throw new ResourceNotFoundException("No employee found with first name: " + firstName);
        }
        return employeesByFirstName;
    }

    @PutMapping(value = "employee/{id}")
    public Employee update(@PathVariable("id") String id, @RequestBody Employee newEmployee) {
        log.info("update() method start");

        final var employee = repository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("No employee found with id: " + id));

        employee.setFirstName(newEmployee.getFirstName());
        employee.setLastName(newEmployee.getLastName());
        employee.setEmail(newEmployee.getEmail());

        final var updatedEmployee = repository.save(employee);
        log.info("updated employee: {}", updatedEmployee);

        return updatedEmployee;
    }

    @PostMapping(value = "employee")
    public Employee save(@RequestBody Employee employee) {
        log.info("save() method start");
        log.info("new employee: {}", employee);
        return repository.save(employee);
    }

    @DeleteMapping(value = "employee/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        log.info("delete() method start");

        final var employee = repository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("No employee found with id: " + id));

        repository.delete(employee);
        return ResponseEntity.ok().build();
    }
}
