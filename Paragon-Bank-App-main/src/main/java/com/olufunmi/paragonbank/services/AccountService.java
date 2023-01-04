package com.olufunmi.paragonbank.services;

import com.olufunmi.paragonbank.dtos.DepositReponses;
import com.olufunmi.paragonbank.dtos.DepositRequest;
import com.olufunmi.paragonbank.dtos.requests.CreateAccountRequests;
import com.olufunmi.paragonbank.dtos.requests.WithdralRequest;
import com.olufunmi.paragonbank.dtos.responses.CreateAccountResponses;
import com.olufunmi.paragonbank.dtos.responses.WithdrawalRespponses;

public interface AccountService {
    CreateAccountResponses createAccount(CreateAccountRequests accountRequests);

//    DepositReponses deposit(DepositRequest request);
    WithdrawalRespponses withdraw(WithdralRequest withdralRequest);

}
