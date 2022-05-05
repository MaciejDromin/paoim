package pl.mlisowski.lab4.web

import org.springframework.beans.factory.annotation.Autowired
import pl.mlisowski.lab4.BaseITSpec

class ClassControllerITSpec extends BaseITSpec {

    @Autowired
    private ClassControllerITSpec classController

    def "should load context" () {
        expect: "Properly loads application context"
        classController
    }

}
