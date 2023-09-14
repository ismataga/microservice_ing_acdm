package com.example.ingressspringfirst.controller

import com.example.ingressspringfirst.exception.ErrorHandler
import com.example.ingressspringfirst.model.request.UserRequest
import com.example.ingressspringfirst.model.response.UserResponse
import com.example.ingressspringfirst.service.UserService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class UserControllerTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private UserService userService
    private MockMvc mockMvc

    def setup() {
        userService = Mock()
        def userController = new UserController(userService)
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }

    def "TestGetUserById"() {

        given:
        def id = random.nextObject(Long)
        def url = "/v1/users/$id"

        def dataResponse = new UserResponse(1L, "ali", 16)


        def expectedResponse = """
                                         {
                                               "id": 1,
                                               "username": "ali",
                                               "age": 16
                                         }
                                      """

        when:
        def result = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then:
        1 * userService.getUser(id) >> dataResponse

        result.response.status == HttpStatus.OK.value()

        JSONAssert.assertEquals(expectedResponse, result.response.contentAsString, true)


    }

    def "TestSaveUser"() {

        given:
        def url = "/v1/users/"
        def userRequest = new UserRequest("ali", 16)

        def jsonRequest = """ 
                                         {
                                               "username": "ali",
                                               "age": 16
                                         }
                                """

        when:
        def result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andReturn()

        then:
        1 * userService.saveUser(userRequest)

        result.response.status == HttpStatus.CREATED.value()

    }


}
