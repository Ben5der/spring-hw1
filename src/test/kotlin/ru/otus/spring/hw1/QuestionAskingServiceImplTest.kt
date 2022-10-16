package ru.otus.spring.hw1

import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import ru.otus.spring.hw1.model.AnswerOption
import ru.otus.spring.hw1.model.Question
import ru.otus.spring.hw1.service.IOService
import ru.otus.spring.hw1.service.LocalizationService
import ru.otus.spring.hw1.service.impl.QuestionAskingServiceImpl
import kotlin.test.assertFalse

class QuestionAskingServiceImplTest {

    private val ioService: IOService = mock()
    private val localizationService: LocalizationService = mock()

    private val questionAskingService = QuestionAskingServiceImpl(ioService, localizationService)

    private val question = Question(
        1,
        "123",
        answerOptions = listOf(
            AnswerOption(1, "1", true),
            AnswerOption(2, "2", false)
        )
    )

    @Test
    fun `user entered not a number`() {
        val enterValue = "bad value"
        whenever(ioService.readln()).thenReturn(enterValue).thenReturn("1")
        questionAskingService.askQuestionAndReturnResult(question)
//        verify(ioService).println(QuestionAskingServiceImpl.WRONG_ANSWER_FORMAT_MESSAGE)
    }

    @Test
    fun `user entered out of range number`() {
        val enterValue = "5"
        whenever(ioService.readln()).thenReturn(enterValue).thenReturn("1")
        questionAskingService.askQuestionAndReturnResult(question)
//        verify(ioService).println(matches(QuestionAskingServiceImpl.ENTERED_ANSWER_OUT_OF_RANGE_MESSAGE))
    }

    @Test
    fun `user entered right answer`() {
        whenever(ioService.readln()).thenReturn("1")
        val result = questionAskingService.askQuestionAndReturnResult(question)
        assert(result)
    }

    @Test
    fun `user entered wrong answer`() {
        whenever(ioService.readln()).thenReturn("2")
        val result = questionAskingService.askQuestionAndReturnResult(question)
        assertFalse(result)
    }
}