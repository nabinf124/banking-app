package com.budhathoki.edu.banking_app.service.impl;

import com.budhathoki.edu.banking_app.dto.AccountDto;
import com.budhathoki.edu.banking_app.entities.Account;
import com.budhathoki.edu.banking_app.mapper.AccountMapper;
import com.budhathoki.edu.banking_app.repository.AccountRepository;
import com.budhathoki.edu.banking_app.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(long id) {
       Account account =accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Doesn't exist"));

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account =accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account Doesn't exist"));
        double total=account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account =accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account Doesn't exist"));
        if(account.getBalance()< amount){
            throw new RuntimeException("Insufficient amount");
        }
        double total= account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts= accountRepository.findAll();
       return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(long id) {
        Account account= accountRepository.findById(id).
                orElseThrow(()-> new RuntimeException("No Account exist"));
        accountRepository.deleteById(id);
    }


}
