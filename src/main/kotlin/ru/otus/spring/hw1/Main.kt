package ru.otus.spring.hw1

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import ru.otus.spring.hw1.service.TestingService

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val context = AnnotationConfigApplicationContext(Main::class.java)
            val testingService = context.getBean(TestingService::class.java)
            testingService.startTesting()
        }
    }
}

