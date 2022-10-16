package ru.otus.spring.hw1.service

interface LocalizationService {
    fun getLocalizedMessage(code: String, vararg params: Any): String
}