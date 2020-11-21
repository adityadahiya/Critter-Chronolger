package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByPetId(long petId) {
        List<Schedule> scheduleList = scheduleRepository.getAllSchedulesByPetList_Id(petId);
        return scheduleList;
    }

    public List<Schedule>  getScheduleByEmployeeId(long eId) {
        List<Schedule>  scheduleList = scheduleRepository.getAllSchedulesByEmployeeList_Id(eId);
        return scheduleList;
    }

    public List<Schedule>  getScheduleByCustomerId(long cId) {
        Customer customer = customerRepository.getOne(cId);
        List<Schedule>  scheduleList = scheduleRepository.getAllSchedulesByPetListIn(customer.getPets());
        return scheduleList;
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

}
