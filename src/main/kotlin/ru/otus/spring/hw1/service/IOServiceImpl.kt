package ru.otus.spring.hw1.service

import org.springframework.stereotype.Service

@Service
class IOServiceImpl : IOService {
    override fun println(message: String) {
        kotlin.io.println(message)
    }

    override fun read() = readln()
}