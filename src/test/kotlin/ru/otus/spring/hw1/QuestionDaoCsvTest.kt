package ru.otus.spring.hw1

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import ru.otus.spring.hw1.dao.QuestionDaoCsv
import ru.otus.spring.hw1.exception.QuestionDaoCsvNotFoundRightAnswerException
import java.io.FileNotFoundException
import kotlin.test.assertEquals


class QuestionDaoCsvTest {

    @Test
    fun `try read csv file with right path`() {
        val questionDaoCsv = QuestionDaoCsv(RIGHT_FILE_PATH)
        assertDoesNotThrow { questionDaoCsv.getQuestions() }
    }

    @Test
    fun `try read csv file with wrong file path`() {
        val questionDaoCsv = QuestionDaoCsv(WRONG_FILE_PATH)
        assertThrows<FileNotFoundException> { questionDaoCsv.getQuestions() }
    }

    @Test
    fun `check question and answers count`() {
        val questionDaoCsv = QuestionDaoCsv(RIGHT_FILE_PATH)
        val question = questionDaoCsv.getQuestions()
        assertEquals(2, question.size)
        assertEquals(2, question[0].answerOptions.size)
    }

    @Test
    fun `try read csv file with required field null values`() {
        val questionDaoCsv = QuestionDaoCsv(FILE_WITH_NULL_VALUES_PATH)
        assertThrows<RuntimeException> { questionDaoCsv.getQuestions() }
    }

    @Test
    fun `try read csv file with question without right answer option`() {
        val questionDaoCsv = QuestionDaoCsv(CSV_WITHOUT_RIGHT_ANSWER_PATH)
        assertThrows<QuestionDaoCsvNotFoundRightAnswerException> { questionDaoCsv.getQuestions() }
    }

    companion object {
        const val RIGHT_FILE_PATH = "test.csv"
        const val WRONG_FILE_PATH = "test123.csv"
        const val FILE_WITH_NULL_VALUES_PATH = "test-with-null.csv"
        const val CSV_WITHOUT_RIGHT_ANSWER_PATH = "test-without-right-answer.csv"
    }
}