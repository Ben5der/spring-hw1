package ru.otus.spring.hw1.service

import ru.otus.spring.hw1.model.UserInfo

interface UserInfoService {
    fun getUserInfo(): UserInfo
}