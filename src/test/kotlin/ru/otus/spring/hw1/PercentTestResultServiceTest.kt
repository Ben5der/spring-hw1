package ru.otus.spring.hw1

import org.junit.Before
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ru.otus.spring.hw1.service.IOService
import ru.otus.spring.hw1.service.LocalizationService
import ru.otus.spring.hw1.service.impl.PercentTestResultService

class PercentTestResultServiceTest {

    private val ioService: IOService = mock()
    private val localizationService: LocalizationService = mock()

    private val percentTestResultService = PercentTestResultService(ioService, localizationService)

    @Before
    fun localizationServiceConfig() {
        whenever(localizationService.getLocalizedMessage(any())).thenReturn("123")
    }

    @Test
    fun `current print call count`() {
        percentTestResultService.calculateAndPrintTestResults(3, 5)
        verify(localizationService).getLocalizedMessage(any(), eq(60.0))
    }
}