package ru.otus.spring.hw1.model

data class Question(val number: Int, val question: String, val answerOptions: List<AnswerOption>)