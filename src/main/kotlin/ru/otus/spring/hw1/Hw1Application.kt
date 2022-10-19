package ru.otus.spring.hw1

import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import ru.otus.spring.hw1.service.TestingService

fun main(args: Array<String>) {
    val context: ApplicationContext = ClassPathXmlApplicationContext("spring-context.xml")
    val testingService = context.getBean(TestingService::class.java)
    testingService.startTesting()
}
