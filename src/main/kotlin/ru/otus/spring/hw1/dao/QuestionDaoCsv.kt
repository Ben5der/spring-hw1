package ru.otus.spring.hw1.dao

import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.otus.spring.hw1.exception.QuestionDaoCsvNotFoundRightAnswerException
import ru.otus.spring.hw1.model.AnswerOption
import ru.otus.spring.hw1.model.Question
import java.io.FileNotFoundException
import java.io.InputStreamReader

@Component
class QuestionDaoCsv(
    @Value("\${test.questions.csv.path}")
    private val path: String
) : QuestionDao {
    override fun getQuestions(): List<Question> {
        val csvFileIS = this::class.java.classLoader.getResourceAsStream(path)
            ?: throw FileNotFoundException("Файл $path не найден")
        val questionCsvModelLists = CsvToBeanBuilder<QuestionCsvModel>(InputStreamReader(csvFileIS))
            .withType(QuestionCsvModel::class.java)
            .build()
            .parse()
        return questionCsvModelLists.toTestAnswerList()
    }

    private fun List<QuestionCsvModel>.toTestAnswerList() =
        this
            .groupBy { it.questionId }
            .map {
                val firstQuestionModel = it.value.first()
                val answerOptions =
                    it.value.mapIndexed { answerNumber, testCsvModel -> testCsvModel.toAnswerOption(answerNumber + 1) }
                answerOptions.firstOrNull { answer -> answer.isCorrect }
                    ?: throw QuestionDaoCsvNotFoundRightAnswerException("Не задан правильный ответ для вопроса ")
                Question(
                    number = it.key!!,
                    question = firstQuestionModel.question!!,
                    answerOptions = answerOptions
                )
            }

    private fun QuestionCsvModel.toAnswerOption(answerNumber: Int) =
        AnswerOption(answerNumber, this.answer!!, this.isCorrectAnswer!!)

    data class QuestionCsvModel(
        @field:CsvBindByName(column = "question_id", required = true)
        var questionId: Int? = null,
        @field:CsvBindByName(column = "question", required = true)
        var question: String? = null,
        @field:CsvBindByName(column = "answer", required = true)
        var answer: String? = null,
        @field:CsvBindByName(column = "is_correct_answer", required = true)
        var isCorrectAnswer: Boolean? = null,
    )
}