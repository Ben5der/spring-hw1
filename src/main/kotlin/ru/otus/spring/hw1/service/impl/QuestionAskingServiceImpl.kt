package ru.otus.spring.hw1.service.impl

import org.springframework.stereotype.Service
import ru.otus.spring.hw1.model.Question
import ru.otus.spring.hw1.service.IOService
import ru.otus.spring.hw1.service.LocalizationService
import ru.otus.spring.hw1.service.QuestionAskingService

@Service
class QuestionAskingServiceImpl(
    private val ioService: IOService,
    private val localizationService: LocalizationService
) : QuestionAskingService {

    private val enterAnswerNumberMessage = localizationService.getLocalizedMessage("enter-answer-number")
    private val wrongAnswerFormatMessage = localizationService.getLocalizedMessage("wrong-answer-format")

    override fun askQuestionAndReturnResult(question: Question): Boolean {
        val answerOptionCount = question.answerOptions.size
        while (true) {
            try {
                question.printQuestion()
                val userAnswerNumber = ioService.readln().toInt()
                if (userAnswerNumber < 1 || userAnswerNumber > answerOptionCount) {
                    val enteredAnswerOutOfRangeMessage =
                        localizationService.getLocalizedMessage("entered-answer-out-of-range", answerOptionCount)
                    ioService.println(enteredAnswerOutOfRangeMessage)
                    continue
                }
                return question.answerOptions.first { it.number == userAnswerNumber }.isCorrect
            } catch (e: RuntimeException) {
                ioService.println(wrongAnswerFormatMessage)
            }
        }
    }

    private fun Question.printQuestion() {
        ioService.println("${this.number}: ${this.question}")
        this.answerOptions.forEach {
            ioService.println("\t${it.number}: ${it.answer}")
        }
        ioService.println(enterAnswerNumberMessage)
    }
}