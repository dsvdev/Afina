package tech.dsvdev.service

import org.springframework.stereotype.Service
import tech.dsvdev.model.TelegramUser
import tech.dsvdev.model.UserState

@Service
class UserServiceImpl : UserService{
    private val users = HashMap<Long, TelegramUser>()
    override fun getById(id: Long): TelegramUser {
        if (!users.containsKey(id)) {
            users[id] = TelegramUser(id, UserState.UNREGISTERED, "")
        }
        return users[id]!!
    }

    override fun save(user: TelegramUser): TelegramUser {
        users[user.telegramId] = user
        return getById(user.telegramId)
    }
}
