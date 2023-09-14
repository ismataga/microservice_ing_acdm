package com.example.ingressspringfirst.service

import com.example.ingressspringfirst.entity.UserEntity
import com.example.ingressspringfirst.exception.NotFoundException
import com.example.ingressspringfirst.repository.UserRepository
import com.example.ingressspringfirst.service.UserService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class UserServiceTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private UserRepository userRepository
    private UserService userService

    def setup() {
        userRepository = Mock()
        userService = new UserService(userRepository)
    }

    def "TestGetUserById success case"() {

        given:
        def id = random.nextObject(Long)
        def entity = random.nextObject(UserEntity)

        when:
        def userResponse = userService.getUser(id)

        then:
        1 * userRepository.findById(id) >> Optional.of(entity)
        userResponse.id == entity.id
        userResponse.age == entity.age
        userResponse.username == entity.username

    }

    def "TestGetUserById user not found case"() {

        given:
        def id = random.nextObject(Long)

        when:
        userService.getUser(id)

        then:
        1 * userRepository.findById(id) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.message == "User not found"

    }
}
