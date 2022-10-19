package ru.otus.spring.hw1.service.impl

import org.springframework.stereotype.Service
import ru.otus.spring.hw1.model.TestRightAnswerCountInfo
import ru.otus.spring.hw1.model.UserInfo
import ru.otus.spring.hw1.service.IOService
import ru.otus.spring.hw1.service.TestResultService

@Service
class PercentTestResultService(
    private val ioService: IOService
) : TestResultService {
    override fun calculateAndPrintTestResults(userInfo: UserInfo, testRightAnswerCountInfo: TestRightAnswerCountInfo) {
        val percentTestResult =
            testRightAnswerCountInfo.rightAnswerCount * 100.0 / testRightAnswerCountInfo.questionsCount
        ioService.println("${userInfo.fullName} $TEST_RESULT_MESSAGE: $percentTestResult%")
    }

    companion object {
        const val TEST_RESULT_MESSAGE = "Your score for the test"
    }
}