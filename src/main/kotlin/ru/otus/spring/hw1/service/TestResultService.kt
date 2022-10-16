package ru.otus.spring.hw1.service

interface TestResultService {
    fun calculateAndPrintTestResults(currentAnswersCount: Int, questionsCount: Int)
}