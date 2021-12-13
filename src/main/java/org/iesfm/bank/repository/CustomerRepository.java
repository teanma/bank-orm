package org.iesfm.bank.repository;

import org.iesfm.bank.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //List<Customer> listCustomers();

    //boolean insertCustomer(Customer customer);

    //boolean deleteCustomer(int id);

    //Customer getCustomer(int id);
}
