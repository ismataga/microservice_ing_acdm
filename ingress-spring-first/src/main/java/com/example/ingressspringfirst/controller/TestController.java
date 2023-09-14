package com.example.ingressspringfirst.controller;

import com.example.ingressspringfirst.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping
    public void test()throws Exception{
        testService.test4();
    }
}
