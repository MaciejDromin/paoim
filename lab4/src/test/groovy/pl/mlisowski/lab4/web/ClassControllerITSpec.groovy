package pl.mlisowski.lab4.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import pl.mlisowski.lab4.domain.Class
import pl.mlisowski.lab4.BaseITSpec
import pl.mlisowski.lab4.application.port.ClassRepository

@AutoConfigureMockMvc
class ClassControllerITSpec extends BaseITSpec {

    @Autowired
    ClassRepository classRepository

    @Autowired
    MockMvc mockMvc

    def "Should return Class list" () {
        given: "Class exists"
        Class c = classRepository.save(new Class())
        expect:
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/class"))
                .andReturn()
                .response
                .contentAsString
        response.contains(c.getUuid())
    }

}
