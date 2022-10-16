package ru.otus.spring.hw1.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "test")
data class TestProperty(val questionsCsvFileName: String, val languageTag: String? = null)