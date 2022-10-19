package ru.otus.spring.hw1.service.impl

import org.springframework.stereotype.Service
import ru.otus.spring.hw1.model.UserInfo
import ru.otus.spring.hw1.service.IOService
import ru.otus.spring.hw1.service.UserInfoService

@Service
class InputUserInfoService(private val ioService: IOService) : UserInfoService {
    override fun getUserInfo(): UserInfo {

        val firstname = readName("Enter your firstname")
        val surname = readName("Enter your surname")
        return UserInfo(firstname = firstname, surname = surname)
    }

    private fun readName(message: String): String {
        while (true) {
            ioService.println(message)
            val name = ioService.readln()
            if (name.isNotBlank())
                return name
            else
                ioService.println("Name cannot be empty")
        }
    }
}