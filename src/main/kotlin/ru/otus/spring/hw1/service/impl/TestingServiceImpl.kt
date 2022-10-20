package ru.otus.spring.hw1.service.impl

import org.springframework.stereotype.Service
import ru.otus.spring.hw1.dao.QuestionDao
import ru.otus.spring.hw1.model.TestRightAnswerCountInfo
import ru.otus.spring.hw1.service.QuestionAskingService
import ru.otus.spring.hw1.service.TestResultService
import ru.otus.spring.hw1.service.TestingService
import ru.otus.spring.hw1.service.UserInfoService

@Service
class TestingServiceImpl(
    private val questionDao: QuestionDao,
    private val userInfoService: UserInfoService,
    private val questionAskingService: QuestionAskingService,
    private val testResultService: TestResultService
) : TestingService {
    override fun startTesting() {
        val questions = questionDao.getQuestions()
        val questionsCount = questions.size
        val userName = userInfoService.getUserInfo()
        val rightAnswerCount = questions
            .map { questionAskingService.askQuestionAndReturnResult(it) }
            .filter { it }
            .size
        val testRightAnswerCountInfo =
            TestRightAnswerCountInfo(rightAnswerCount = rightAnswerCount, questionsCount = questionsCount)
        testResultService.calculateAndPrintTestResults(userName, testRightAnswerCountInfo)
    }
}