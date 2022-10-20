package ru.otus.spring.hw1.service

import ru.otus.spring.hw1.model.Question

interface QuestionAskingService {
    fun askQuestionAndReturnResult(question: Question): Boolean
}