package ru.otus.spring.hw1.service

import ru.otus.spring.hw1.dao.QuestionDao
import ru.otus.spring.hw1.model.Question

class TestingServiceImpl(
    private val questionDao: QuestionDao,
    private val ioService: IOService
) : TestingService {
    override fun startTesting() {
        val testQuestionList = questionDao.getQuestions()
        testQuestionList.printTest()
    }

    private fun List<Question>.printTest() =
        this.forEach { question ->
            ioService.println("Question ${question.number}: ${question.question}")
            question.answerOptions.forEach { answerOption ->
                ioService.println("Answer ${answerOption.number + 1}: ${answerOption.answer}")
            }
        }
}