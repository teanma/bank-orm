package org.iesfm.bank.controller;

import org.iesfm.bank.Account;
import org.iesfm.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/accounts")
    public List<Account> listAccounts() {
        return accountRepository.findAll();
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account doesn't exist");
        }
    }
}
