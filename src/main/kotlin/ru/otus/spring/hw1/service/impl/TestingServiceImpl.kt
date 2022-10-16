package ru.otus.spring.hw1.service.impl

import org.springframework.stereotype.Service
import ru.otus.spring.hw1.dao.QuestionDao
import ru.otus.spring.hw1.service.QuestionAskingService
import ru.otus.spring.hw1.service.TestResultService
import ru.otus.spring.hw1.service.TestingService

@Service
class TestingServiceImpl(
    private val questionDao: QuestionDao,
    private val questionAskingService: QuestionAskingService,
    private val testResultService: TestResultService
) : TestingService {
    override fun startTesting() {
        val questions = questionDao.getQuestions()
        val questionsCount = questions.size
        val rightAnswerCount = questions
            .map { questionAskingService.askQuestionAndReturnResult(it) }
            .filter { it }
            .size
        testResultService.calculateAndPrintTestResults(rightAnswerCount, questionsCount)
    }
}