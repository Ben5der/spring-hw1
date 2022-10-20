package ru.otus.spring.hw1

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import ru.otus.spring.hw1.model.TestRightAnswerCountInfo
import ru.otus.spring.hw1.model.UserInfo
import ru.otus.spring.hw1.service.IOService
import ru.otus.spring.hw1.service.impl.PercentTestResultService

class PercentTestResultServiceTestInfo {

    private val ioService: IOService = mock()

    private val percentTestResultService = PercentTestResultService(ioService)

    @Test
    fun `current print call count`() {
        val userInfo = UserInfo("Test", "User")
        percentTestResultService.calculateAndPrintTestResults(userInfo, TestRightAnswerCountInfo(3, 5))
        verify(ioService).println(Mockito.matches(userInfo.fullName))
        verify(ioService).println(Mockito.matches("60.0%"))
    }
}