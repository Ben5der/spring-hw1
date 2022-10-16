package ru.otus.spring.hw1.service

import org.springframework.stereotype.Service
import ru.otus.spring.hw1.model.Question

@Service
class QuestionAskingServiceImpl(private val ioService: IOService) : QuestionAskingService {
    override fun askQuestionAndReturnResult(question: Question): Boolean {
        val answerOptionCount = question.answerOptions.size
        while (true) {
            try {
                question.printQuestion()
                val userAnswerNumber = ioService.read().toInt()
                if (userAnswerNumber < 1 || userAnswerNumber > answerOptionCount) {
                    ioService.println("$ENTERED_ANSWER_OUT_OF_RANGE_MESSAGE from 1 to $answerOptionCount")
                    continue
                }
                return question.answerOptions.first { it.number == userAnswerNumber }.isCorrect
            } catch (e: RuntimeException) {
                ioService.println(WRONG_ANSWER_FORMAT_MESSAGE)
            }
        }
    }

    private fun Question.printQuestion() {
        ioService.println("Question ${this.number}: ${this.question}")
        this.answerOptions.forEach {
            ioService.println("${it.number}: ${it.answer}")
        }
        ioService.println(ENTER_ANSWER_NUMBER_MESSAGE)
    }

    companion object {
        const val ENTER_ANSWER_NUMBER_MESSAGE = "Please enter the number of the correct answer"
        const val WRONG_ANSWER_FORMAT_MESSAGE = "Wrong answer format, please enter answer number"
        const val ENTERED_ANSWER_OUT_OF_RANGE_MESSAGE = "Wrong answer format, answer number must be in range"
    }
}