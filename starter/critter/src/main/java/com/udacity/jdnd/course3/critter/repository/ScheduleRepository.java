package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> getAllSchedulesByPetList_Id(long petId);

    List<Schedule> getAllSchedulesByEmployeeList_Id(long eId);

    List<Schedule> getAllSchedulesByPetListIn(List<Pet> pets);
}
