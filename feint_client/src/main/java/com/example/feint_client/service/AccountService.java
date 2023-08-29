package com.example.feint_client.service;

import static com.example.feint_client.mapper.UserMapper.buildUserRequest;
import static com.example.feint_client.model.constant.CurrencyConstant.eligibleCurrencies;

import com.example.feint_client.client.UserClient;
import com.example.feint_client.model.client.AccountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserClient userClient;

    public void validateAccount(AccountRequest account) {
        if (eligibleCurrencies.contains(account.getCurrency())) {
            throw new RuntimeException("NOT ELIGIBLE CIRRENCY");
        }

        userClient.addUser(buildUserRequest());
    }
}
