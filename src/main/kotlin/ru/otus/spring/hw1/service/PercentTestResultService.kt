package ru.otus.spring.hw1.service

import org.springframework.stereotype.Service

@Service
class PercentTestResultService(
    private val ioService: IOService
) : TestResultService {
    override fun calculateAndPrintTestResults(currentAnswersCount: Int, questionsCount: Int) {
        val percentTestResult = currentAnswersCount * 100.0 / questionsCount
        ioService.println("$TEST_RESULT_MESSAGE: $percentTestResult%")
    }

    companion object {
        const val TEST_RESULT_MESSAGE = "Your score for the test"
    }
}