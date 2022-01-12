package org.iesfm.bank.controller;

import org.iesfm.bank.Account;
import org.iesfm.bank.Customer;
import org.iesfm.bank.repository.AccountRepository;
import org.iesfm.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/accounts")
    public List<Account> listAccounts() {
        return accountRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/accounts")
    public void insertAccount(@RequestBody Account account) {
        if(accountRepository.existsById(account.getAccountIban())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account already exists");
        } else {
            accountRepository.save(account);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/accounts/{iban}")
    public Account getAccount(@PathVariable("iban") String iban) {
        Optional<Account> accountOptional = accountRepository.findById(iban);
        if (accountOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else {
            return accountOptional.get();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/accounts/{iban}")
    public void deleteAccount(@PathVariable("iban") String iban) {
        if (accountRepository.existsById(iban)) {
            accountRepository.deleteById(iban);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/customers/{id}/accounts")
    public List<Account> listCustomerAccounts(@PathVariable("id") int customerId){
        return accountRepository.findByOwnerId(customerId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/customers/{id}/accounts")
    public void insertAccountToCustomer(@PathVariable("id") int customerId, @RequestBody Account account){
        if (!customerRepository.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer doesn't exists");
        } else {
            accountRepository.save(account);
        }
    }
}
