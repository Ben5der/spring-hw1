package ru.otus.spring.hw1.service.impl

import org.springframework.stereotype.Service
import ru.otus.spring.hw1.service.IOService
import ru.otus.spring.hw1.service.LocalizationService
import ru.otus.spring.hw1.service.TestResultService

@Service
class PercentTestResultService(
    private val ioService: IOService,
    private val localizationService: LocalizationService
) : TestResultService {
    override fun calculateAndPrintTestResults(currentAnswersCount: Int, questionsCount: Int) {
        val percentTestResult = currentAnswersCount * 100.0 / questionsCount
        val testResultMessages = localizationService.getLocalizedMessage("test-result", percentTestResult)
        ioService.println(testResultMessages)
    }
}