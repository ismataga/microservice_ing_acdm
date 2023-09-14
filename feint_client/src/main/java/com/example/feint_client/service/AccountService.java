package com.example.feint_client.service;

import static com.example.feint_client.model.constant.CurrencyConstant.eligibleCurrencies;

import com.example.feint_client.model.client.AccountRequest;
import com.example.feint_client.model.client.UserRequest;
import com.example.feint_client.model.queue.UserDto;
import com.example.feint_client.queue.QueueSender;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AsyncService asyncService;
    private final QueueSender queueSender;

    @SneakyThrows
    public void validateAccount(AccountRequest account) {
        if (!eligibleCurrencies.contains(account.getCurrency())) {
            throw new RuntimeException("NOT ELIGIBLE CURRENCY");
        }
        asyncService.saveUser();
    }

    @PostConstruct
    public void test(){
        queueSender.sendToUserQueue(
                "USER_Q", new UserDto("Ali",16));
    }
}
