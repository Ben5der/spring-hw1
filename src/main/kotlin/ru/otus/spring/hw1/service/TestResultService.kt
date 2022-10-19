package ru.otus.spring.hw1.service

import ru.otus.spring.hw1.model.TestRightAnswerCountInfo
import ru.otus.spring.hw1.model.UserInfo

interface TestResultService {
    fun calculateAndPrintTestResults(userInfo: UserInfo, testRightAnswerCountInfo: TestRightAnswerCountInfo)
}