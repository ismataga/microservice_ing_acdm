package com.example.ingressspringfirst.service;

import com.example.ingressspringfirst.entity.UserEntity;
import com.example.ingressspringfirst.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final UserRepository userRepository;

    @Transactional
    public void test() throws Exception {
        test3();
    }

    @Transactional
    public void test3() throws Exception {
        userRepository.save(new UserEntity(null, "e", 4,
                "f", null, null));
        test2();

    }

    @Transactional
    public void test4() throws Exception {
        var user = userRepository.findById(1L).get();
        user.setUsername("ali");

        var user1 = userRepository.save(new UserEntity(null, "e", 4,
                "f", null, null));

        user1.setBirthPlace("Baki");

    }

    public void test2() throws Exception {
        throw new Exception();

    }


}
