package pl.mlisowski.lab4

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = [Lab4Application])
abstract class BaseITSpec extends Specification {
}
