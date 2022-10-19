package ru.otus.spring.hw1.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.otus.spring.hw1.service.IOService
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintStream

@Service
class StreamIOService(
    @Value("#{ T(java.lang.System).in }")
    private val inputStream: InputStream,
    @Value("#{ T(java.lang.System).out }")
    private val printStream: PrintStream
) : IOService {
    override fun println(message: String) {
        printStream.println(message)
    }

    override fun readln() = BufferedReader(InputStreamReader(inputStream)).readLine() ?: ""
}