package tech.dsvdev.processor.message.registration

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Message
import tech.dsvdev.model.BotAnswer
import tech.dsvdev.model.UserState
import tech.dsvdev.processor.MessageProcessor
import tech.dsvdev.service.UserService

@Component
class NameInputProcessor(
    private val userService: UserService
) : MessageProcessor {
    override fun process(data: Message): BotAnswer {
        val user = userService.getById(data.from.id)
        user.name = data.text
        userService.save(user)

        return BotAnswer(
            text = "Ваше имя - ${user.name}\nПодвердить?",
            newState = UserState.NAME_APPROVE,
            chatId = data.chatId.toString()
        )
    }
}