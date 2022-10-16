package ru.otus.spring.hw1

import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ru.otus.spring.hw1.dao.QuestionDao
import ru.otus.spring.hw1.model.AnswerOption
import ru.otus.spring.hw1.model.Question
import ru.otus.spring.hw1.service.QuestionAskingService
import ru.otus.spring.hw1.service.TestResultService
import ru.otus.spring.hw1.service.impl.TestingServiceImpl


class TestingServiceImplTest {
    private val questionDao: QuestionDao = mock()
    private val questionAskingService: QuestionAskingService = mock()
    private val testResultService: TestResultService = mock()

    private val testingServiceImpl = TestingServiceImpl(questionDao, questionAskingService, testResultService)
    private val questions = listOf(
        Question(
            1,
            "123",
            answerOptions = listOf(
                AnswerOption(1, "1", true),
                AnswerOption(1, "2", false)
            )
        ),
        Question(
            2,
            "456",
            answerOptions = listOf(
                AnswerOption(1, "3", true),
                AnswerOption(1, "4", false)
            )
        )
    )

    @Test
    fun `correct count call ask questions`() {
        whenever(questionDao.getQuestions()).thenReturn(questions)
        testingServiceImpl.startTesting()
//        val exceptedPrintCall = questions.size + questions.sumOf { it.answerOptions.size }
        verify(questionAskingService).askQuestionAndReturnResult(questions[0])
        verify(questionAskingService).askQuestionAndReturnResult(questions[1])
        verify(questionAskingService, times(questions.size)).askQuestionAndReturnResult(any())
    }

    @Test
    fun `call ask questions`() {
        whenever(questionDao.getQuestions()).thenReturn(questions)
        testingServiceImpl.startTesting()
        verify(testResultService).calculateAndPrintTestResults(any(), eq(2))
    }

    @Test
    fun `correct calk right answers`() {
        whenever(questionDao.getQuestions()).thenReturn(questions)
        whenever(questionAskingService.askQuestionAndReturnResult(questions[0])).thenReturn(true)
        whenever(questionAskingService.askQuestionAndReturnResult(questions[1])).thenReturn(false)
        testingServiceImpl.startTesting()
        verify(testResultService).calculateAndPrintTestResults(eq(1), eq(2))
    }
}
