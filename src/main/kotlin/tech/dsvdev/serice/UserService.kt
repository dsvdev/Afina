package tech.dsvdev.serice

import tech.dsvdev.model.TelegramUser

interface UserService {
    fun getById(id: Long) : TelegramUser
    fun save(user: TelegramUser) : TelegramUser
}
