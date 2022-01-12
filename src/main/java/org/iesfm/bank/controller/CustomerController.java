package org.iesfm.bank.controller;

import org.iesfm.bank.Account;
import org.iesfm.bank.Customer;
import org.iesfm.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/customers")
    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/customers")
    public void insertCustomer(@RequestBody Customer customer) {
        if(customerRepository.existsById(customer.getCustomerId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer already exists");
        } else {
            customerRepository.save(customer);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/customers/{id}")
    public Customer getCustomer(@PathVariable("id") int id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        } else {
            return customerOptional.get();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/customers/{id}")
    public void deleteCustomer(@PathVariable("id") int id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }
}
