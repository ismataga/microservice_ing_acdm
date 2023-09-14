package com.example.ingressspringfirst.mapper

import com.example.ingressspringfirst.entity.UserEntity
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class UserMapperTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()


    def "TestMapEntityToResponse"() {

        given:
        def entity = random.nextObject(UserEntity)

        when:
        def userResponse = UserMapper.mapEntityToResponse(entity)

        then:
        userResponse.id == entity.id
        userResponse.age == entity.age
        userResponse.username == entity.username

    }
}
