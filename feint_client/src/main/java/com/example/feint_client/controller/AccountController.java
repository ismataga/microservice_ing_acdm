package com.example.feint_client.controller;

import com.example.feint_client.model.client.AccountRequest;
import com.example.feint_client.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/validate")
    public void validateAccount(@RequestBody AccountRequest account) {
        accountService.validateAccount(account);
    }
}
