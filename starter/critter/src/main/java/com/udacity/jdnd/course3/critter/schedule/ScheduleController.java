package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.createSchedule(convertScheduleDTOToEntity(scheduleDTO));
        return convertEntityToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> scheduleList = scheduleService.getAllSchedules();
        return convertScheduleListToScheduleDTOList(scheduleList);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> scheduleList = scheduleService.getSchedulesByPetId(petId);
        return convertScheduleListToScheduleDTOList(scheduleList);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByEmployeeId(employeeId);
        return convertScheduleListToScheduleDTOList(scheduleList);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByCustomerId(customerId);
        return convertScheduleListToScheduleDTOList(scheduleList);
    }

    private static ScheduleDTO convertEntityToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        List<Long> petIds = new ArrayList<>();
        if (schedule.getPetList() != null) {
            List<Pet> petList = new ArrayList<Pet>();
            petList = schedule.getPetList();
            for (Pet pet : petList) {
                petIds.add(pet.getId());
            }
            scheduleDTO.setPetIds(petIds);
        }
        List<Long> employeeIds = new ArrayList<>();
        if (schedule.getEmployeeList() != null) {
            List<Employee> employeeList = new ArrayList<Employee>();
            employeeList = schedule.getEmployeeList();
            for (Employee emp : employeeList) {
                employeeIds.add(emp.getId());
            }
            scheduleDTO.setEmployeeIds(employeeIds);
        }
        return scheduleDTO;
    }

    private static Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        if (scheduleDTO.getEmployeeIds() != null) {
            List<Employee> employees = new ArrayList<Employee>();
            List<Long> employeeIdList = new ArrayList<Long>();
            employeeIdList = scheduleDTO.getEmployeeIds();
            for (Long empId : employeeIdList) {
                Employee employee = new Employee();
                employee.setId(empId);
                employees.add(employee);
            }
            schedule.setEmployeeList(employees);
        }
        if (scheduleDTO.getPetIds() != null) {
            List<Pet> pets = new ArrayList<Pet>();
            List<Long> petIdList = new ArrayList<Long>();
            petIdList = scheduleDTO.getPetIds();
            for (Long petId : petIdList) {
                Pet pet = new Pet();
                pet.setId(petId);
                pets.add(pet);
            }
            schedule.setPetList(pets);
        }
        return schedule;
    }

    private static List<ScheduleDTO> convertScheduleListToScheduleDTOList(List<Schedule> schedules) {
        List<ScheduleDTO> scheduleDTOList = new ArrayList<ScheduleDTO>();
        for (Schedule schedule : schedules) {
            scheduleDTOList.add(convertEntityToScheduleDTO(schedule));
        }
        return scheduleDTOList;
    }
}
