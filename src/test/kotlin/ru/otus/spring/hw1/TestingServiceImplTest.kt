package ru.otus.spring.hw1

import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ru.otus.spring.hw1.dao.QuestionDao
import ru.otus.spring.hw1.model.AnswerOption
import ru.otus.spring.hw1.model.Question
import ru.otus.spring.hw1.service.IOService
import ru.otus.spring.hw1.service.TestingServiceImpl


class TestingServiceImplTest {
    private val ioService: IOService = mock()
    private val questionDao: QuestionDao = mock()
    private val testingServiceImpl = TestingServiceImpl(questionDao, ioService)
    private val questions = listOf(
        Question(
            1,
            "123",
            answerOptions = listOf(
                AnswerOption(1, "456", true),
                AnswerOption(1, "789", false)
            )
        )
    )

    @Test
    fun `correct count print`() {
        whenever(questionDao.getQuestions()).thenReturn(questions)
        testingServiceImpl.startTesting()
        val exceptedPrintCall = questions.size + questions.sumOf { it.answerOptions.size }
        verify(ioService, times(exceptedPrintCall)).println(any())
    }
}
