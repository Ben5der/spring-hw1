package ru.otus.spring.hw1

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import ru.otus.spring.hw1.service.IOService
import ru.otus.spring.hw1.service.PercentTestResultService

class PercentTestResultServiceTest {

    private val ioService: IOService = mock()

    private val percentTestResultService = PercentTestResultService(ioService)

    @Test
    fun `current print call count`() {
        percentTestResultService.calculateAndPrintTestResults(3, 5)
        verify(ioService).println(Mockito.matches("60.0%"))
    }
}