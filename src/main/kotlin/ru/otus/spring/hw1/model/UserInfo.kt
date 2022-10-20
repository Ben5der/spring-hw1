package ru.otus.spring.hw1.model

data class UserInfo(val firstname: String, val surname: String) {
    val fullName
        get() = "$firstname $surname"
}
