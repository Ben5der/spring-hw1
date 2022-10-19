package ru.otus.spring.hw1

import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ru.otus.spring.hw1.dao.QuestionDao
import ru.otus.spring.hw1.model.AnswerOption
import ru.otus.spring.hw1.model.Question
import ru.otus.spring.hw1.model.TestRightAnswerCountInfo
import ru.otus.spring.hw1.model.UserInfo
import ru.otus.spring.hw1.service.QuestionAskingService
import ru.otus.spring.hw1.service.TestResultService
import ru.otus.spring.hw1.service.UserInfoService
import ru.otus.spring.hw1.service.impl.TestingServiceImpl


class TestingServiceImplTest {
    private val questionDao: QuestionDao = mock()
    private val userInfoService: UserInfoService = mock()
    private val questionAskingService: QuestionAskingService = mock()
    private val testResultService: TestResultService = mock()

    private val testingServiceImpl =
        TestingServiceImpl(questionDao, userInfoService, questionAskingService, testResultService)
    private val questions = listOf(
        Question(
            "123",
            answerOptions = listOf(
                AnswerOption(1, "1", true),
                AnswerOption(1, "2", false)
            )
        ),
        Question(
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
        verify(questionAskingService).askQuestionAndReturnResult(questions[0])
        verify(questionAskingService).askQuestionAndReturnResult(questions[1])
        verify(questionAskingService, times(questions.size)).askQuestionAndReturnResult(any())
    }

    @Test
    fun `correct calk right answers`() {
        val userInfo = UserInfo("Test", "User")

        whenever(questionDao.getQuestions()).thenReturn(questions)
        whenever(userInfoService.getUserInfo()).thenReturn(userInfo)
        whenever(questionAskingService.askQuestionAndReturnResult(questions[0])).thenReturn(true)
        whenever(questionAskingService.askQuestionAndReturnResult(questions[1])).thenReturn(false)
        testingServiceImpl.startTesting()
        verify(testResultService).calculateAndPrintTestResults(eq(userInfo), eq(TestRightAnswerCountInfo(1, 2)))
    }
}
