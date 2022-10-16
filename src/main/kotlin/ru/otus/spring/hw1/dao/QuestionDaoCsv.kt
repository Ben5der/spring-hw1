package ru.otus.spring.hw1.dao

import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.stereotype.Component
import ru.otus.spring.hw1.exception.QuestionDaoCsvNotFoundRightAnswerException
import ru.otus.spring.hw1.model.AnswerOption
import ru.otus.spring.hw1.model.Question
import ru.otus.spring.hw1.property.TestProperty
import ru.otus.spring.hw1.service.LocalizationService
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.util.*

@Component
class QuestionDaoCsv(
    private val testProperty: TestProperty,
    private val localizationService: LocalizationService
) : QuestionDao {

    val locale: Locale =
        if (!testProperty.languageTag.isNullOrEmpty()) Locale.forLanguageTag(testProperty.languageTag) else Locale.getDefault()

    override fun getQuestions(): List<Question> {
        val localizedPath = "${testProperty.questionsCsvFileName}_${locale.language}.csv"
        val csvFileIS = this::class.java.classLoader.getResourceAsStream(localizedPath)
            ?: throwFileNotFoundException(localizedPath)
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
                    ?: throwQuestionDaoCsvNotFoundRightAnswerException(firstQuestionModel.question!!)

                Question(number = it.key!!, question = firstQuestionModel.question!!, answerOptions = answerOptions)
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

    fun throwFileNotFoundException(path: String): Nothing {
        val fileNotFoundMessage = localizationService.getLocalizedMessage("csv-file-not-found", path)
        throw FileNotFoundException(fileNotFoundMessage)
    }

    fun throwQuestionDaoCsvNotFoundRightAnswerException(question: String): Nothing {
        val csvNotFoundRightAnswerMessage =
            localizationService.getLocalizedMessage("csv-not-found-right-answer", question)
        throw QuestionDaoCsvNotFoundRightAnswerException(csvNotFoundRightAnswerMessage)
    }
}