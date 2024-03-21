package tech.dsvdev.processor.message.registration

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Message
import tech.dsvdev.model.BotAnswer
import tech.dsvdev.model.UserState
import tech.dsvdev.processor.MessageProcessor
import tech.dsvdev.service.UserService
import tech.dsvdev.utils.undefinedCommandAnswer

@Component
class NameApproveProcessor(
    private val userService: UserService
) : MessageProcessor {
    private fun undefinedBotAnswer(chatId: String) = BotAnswer(
        text = undefinedCommandAnswer,
        chatId = chatId,
        newState = UserState.NAME_APPROVE
    )
    override fun process(data: Message): BotAnswer {
        return when (data.text.lowercase()) {
            "да" -> nameAccept(data)
            "нет" -> nameDicline(data)
            else -> undefinedBotAnswer(data.chatId.toString())
        }
    }

    private fun nameAccept(data: Message): BotAnswer {
        val user = userService.getById(data.from.id)
        return BotAnswer(
            text = "Приятно познакомится, ${user.name}!",
            chatId = data.chatId.toString(),
            newState = UserState.MAIN
        )
    }

    private fun nameDicline(data: Message): BotAnswer {
        return BotAnswer(
            text = "Имя сброшено, введите другое имя!",
            chatId = data.chatId.toString(),
            newState = UserState.NAME_INPUT
        )
    }


}