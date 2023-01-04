package com.olufunmi.paragonbank.services;

import com.olufunmi.paragonbank.data.models.Account;
import com.olufunmi.paragonbank.data.repositories.AccountRepository;
import com.olufunmi.paragonbank.dtos.DepositReponses;
import com.olufunmi.paragonbank.dtos.DepositRequest;
import com.olufunmi.paragonbank.dtos.requests.CreateAccountRequests;
import com.olufunmi.paragonbank.dtos.requests.WithdralRequest;
import com.olufunmi.paragonbank.dtos.responses.CreateAccountResponses;
import com.olufunmi.paragonbank.dtos.responses.WithdrawalRespponses;
import com.olufunmi.paragonbank.exceptions.AccountNotFoundException;
import com.olufunmi.paragonbank.exceptions.DepositNotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public CreateAccountResponses createAccount(CreateAccountRequests accountRequests) {
        Account account = new Account();
        account.setAccountName(accountRequests.getAccountName());
        account.setPin(accountRequests.getPin());
        account.setBalance(accountRequests.getBalance());
        String accountNumber = account.generateAccNumber();
        account.setAccountNumber(accountNumber);
        accountRepository.save(account);
        return new CreateAccountResponses("Success", accountNumber);
    }

//    @Override
//    public DepositReponses deposit(DepositRequest request) {
//        Optional<Account> accountFound = accountRepository.findByAccountNumber(request.getAccountNumber());
//        if (accountFound.isEmpty()) {
//            throw new AccountNotFoundException("Account Not Exist");
//        }
//        Account account = accountFound.get();
//        if (request.getDepositAmount() < 0){
//            throw new DepositNotAllowedException("Input Positive number")
//        }
////        account.setBalance(BigDecimal.valueOf(request.getDepositAmount()).add(request.getDepositAmount());
//    }


    @Override
    public WithdrawalRespponses withdraw(WithdralRequest withdralRequest) {
        Optional<Account> account =accountRepository.findByAccountNumber(withdralRequest.getAccountNumber());
        if (account.isEmpty()){
            throw new AccountNotFoundException("Account Not Exist");
        }
        Account account1 = account.get();
        if (BigDecimal.valueOf(withdralRequest.getWithdrawAmount()).compareTo(account1.getBalance()) > 0){
            throw new AccountNotFoundException("invalid amount");
        }
        account1.setBalance(account1.getBalance().subtract(BigDecimal.valueOf(withdralRequest.getWithdrawAmount())));
        accountRepository.save(account1);
        return new WithdrawalRespponses("withdraw successful");
    }

}
