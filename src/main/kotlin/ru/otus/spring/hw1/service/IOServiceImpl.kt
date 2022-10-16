package ru.otus.spring.hw1.service

class IOServiceImpl : IOService {
    override fun println(message: String) {
        kotlin.io.println(message)
    }

    override fun read() = readln()
}