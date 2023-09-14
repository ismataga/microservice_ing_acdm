package com.example.feint_client.client;

import com.example.feint_client.model.client.UserRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(value = "!default")
@Service
public class UserClientMock implements UserClient{
    @Override
    public void saveUser(UserRequest user) {
        System.out.println("Assuming everything is ok");
    }
}
