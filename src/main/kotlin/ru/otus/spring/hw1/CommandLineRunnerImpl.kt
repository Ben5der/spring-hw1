package ru.otus.spring.hw1

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ru.otus.spring.hw1.service.TestingService

@Component
class CommandLineRunnerImpl(private val testingService: TestingService) : CommandLineRunner {
    override fun run(vararg args: String) {
        testingService.startTesting()
    }
}