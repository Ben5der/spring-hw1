package ru.otus.spring.hw1.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import ru.otus.spring.hw1.service.LocalizationService
import java.util.*

@Service
class LocalizationServiceImpl(
    private val messageSource: MessageSource,
    @Value("\${test.language-tag:}")
    private val localeName: String?
) : LocalizationService {
    private val locale = if (!localeName.isNullOrBlank()) Locale.forLanguageTag(localeName) else Locale.getDefault()
    override fun getLocalizedMessage(code: String, vararg params: Any): String =
        messageSource.getMessage(code, params, locale)
}