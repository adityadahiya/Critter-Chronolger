package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    public Customer getOwnerByPetId(long petId) {
        Customer customer = customerRepository.getCustomerByPets_Id(petId);
        return customer;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}
