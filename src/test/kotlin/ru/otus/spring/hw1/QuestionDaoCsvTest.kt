package ru.otus.spring.hw1

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.anyVararg
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import ru.otus.spring.hw1.dao.QuestionDaoCsv
import ru.otus.spring.hw1.exception.QuestionDaoCsvNotFoundRightAnswerException
import ru.otus.spring.hw1.property.TestProperty
import ru.otus.spring.hw1.service.LocalizationService
import java.io.FileNotFoundException
import kotlin.test.assertEquals


class QuestionDaoCsvTest {

    private val localizationService: LocalizationService = mock()

    @Test
    fun `try read csv file with right path`() {
        val questionDaoCsv = QuestionDaoCsv(TestProperty(RIGHT_FILE_NAME), localizationService)
        assertDoesNotThrow { questionDaoCsv.getQuestions() }
    }

    @Test
    fun `try read csv file with wrong file path`() {
        val questionDaoCsv = QuestionDaoCsv(TestProperty(WRONG_FILE_NAME), localizationService)
        assertThrows<FileNotFoundException> { questionDaoCsv.getQuestions() }
    }

    @Test
    fun `check question and answers count`() {
        val questionDaoCsv = QuestionDaoCsv(TestProperty(RIGHT_FILE_NAME), localizationService)
        val question = questionDaoCsv.getQuestions()
        assertEquals(2, question.size)
        assertEquals(2, question[0].answerOptions.size)
    }

    @Test
    fun `try read csv file with required field null values`() {
        val questionDaoCsv = QuestionDaoCsv(TestProperty(CSV_WITH_NULL_VALUES_FILE_NAME), localizationService)
        assertThrows<RuntimeException> { questionDaoCsv.getQuestions() }
    }

    @Test
    fun `try read csv file with question without right answer option`() {
        whenever(localizationService.getLocalizedMessage(anyString(), anyVararg())).thenReturn("123")
        val questionDaoCsv = QuestionDaoCsv(TestProperty(CSV_WITHOUT_RIGHT_ANSWER_FILE_NAME), localizationService)
        assertThrows<QuestionDaoCsvNotFoundRightAnswerException> { questionDaoCsv.getQuestions() }
    }

    companion object {
        const val RIGHT_FILE_NAME = "test"
        const val WRONG_FILE_NAME = "test123"
        const val CSV_WITH_NULL_VALUES_FILE_NAME = "test-with-null"
        const val CSV_WITHOUT_RIGHT_ANSWER_FILE_NAME = "test-without-right-answer"
    }
}