package com.example.ingressspringfirst.controller;

import com.example.ingressspringfirst.model.criteria.PageCriteria;
import com.example.ingressspringfirst.model.criteria.UserCriteria;
import com.example.ingressspringfirst.model.request.UserRequest;
import com.example.ingressspringfirst.model.response.PageableUserResponse;
import com.example.ingressspringfirst.model.response.UserResponse;
import com.example.ingressspringfirst.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

//    @GetMapping
//    public List<UserResponse> getUsers() {
//        return userService.getUsers();
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserRequest userRequest) {
        userService.saveUser(userRequest);
    }

    @PatchMapping("/{id}/birth-place")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setBirthPlace(@PathVariable Long id,@RequestHeader String birthPlace){
        userService.setBirthPlace(id,birthPlace);
    }

    @GetMapping
    public PageableUserResponse getUsers(PageCriteria pageCriteria , UserCriteria userCriteria){
        return userService.getUsers(pageCriteria,userCriteria);
    }


}
