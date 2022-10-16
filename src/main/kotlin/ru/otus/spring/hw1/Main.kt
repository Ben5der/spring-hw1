package ru.otus.spring.hw1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class Main

fun main(args: Array<String>) {
    runApplication<Main>(*args)
}
