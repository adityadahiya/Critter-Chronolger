package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Transactional
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Long eId) {
        Employee employee = employeeRepository.getOne(eId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.getOne(id);
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        Set<EmployeeSkill> employeeSkills = employeeDTO.getSkills();
        DayOfWeek dow = employeeDTO.getDate().getDayOfWeek();
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> availableEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getDaysAvailable().contains(dow) && employee.getSkills().containsAll(employeeSkills)) {
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }

}
