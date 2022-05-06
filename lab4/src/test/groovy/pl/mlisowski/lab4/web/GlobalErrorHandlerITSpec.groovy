package pl.mlisowski.lab4.web

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.core.MethodParameter
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.validation.DirectFieldBindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import pl.mlisowski.lab4.BaseITSpec
import pl.mlisowski.lab4.application.ClassService
import spock.lang.Unroll

import javax.persistence.EntityNotFoundException
import javax.validation.ConstraintViolationException

@AutoConfigureMockMvc
class GlobalErrorHandlerITSpec extends BaseITSpec {

    final static String DEFAULT_ERROR_MESSAGE = "Test"

    @SpringBean
    ClassService classService = Mock()

    @Autowired
    MockMvc mockMvc

    @Unroll
    def "Should handle thrown exceptions" () {
        given:
        classService.getAllClasses() >> { throw exception }

        expect:
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/class"))
                .andExpect(MockMvcResultMatchers.status().is(expectedStatus))
                .andReturn()
                .response
                .contentAsString
        response.contains(expectedErrorMessage)

        where:
        exception                                                       | expectedStatus    | expectedErrorMessage
        new EmptyResultDataAccessException(1)                           | 404               | "1"
        new EntityNotFoundException(DEFAULT_ERROR_MESSAGE)              | 404               | DEFAULT_ERROR_MESSAGE
        new ConstraintViolationException(DEFAULT_ERROR_MESSAGE, null)   | 400               | DEFAULT_ERROR_MESSAGE
        new NullPointerException(DEFAULT_ERROR_MESSAGE)                 | 500               | DEFAULT_ERROR_MESSAGE
    }

}
