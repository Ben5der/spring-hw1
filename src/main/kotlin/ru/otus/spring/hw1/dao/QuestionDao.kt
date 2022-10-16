package ru.otus.spring.hw1.dao

import ru.otus.spring.hw1.model.Question

interface QuestionDao {
    fun getQuestions(): List<Question>
}