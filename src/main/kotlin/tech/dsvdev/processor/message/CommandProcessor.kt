package tech.dsvdev.processor.message

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Message
import tech.dsvdev.model.BotAnswer
import tech.dsvdev.model.BotCommands
import tech.dsvdev.model.UserState
import tech.dsvdev.model.toBotCommand
import tech.dsvdev.processor.MessageProcessor

@Component
class CommandProcessor : MessageProcessor {
    override fun process(data: Message): BotAnswer {
        if (data.text !in BotCommands.entries.map { it.command }) throw IllegalArgumentException()

        return when (data.text.toBotCommand()) {
            BotCommands.ABOUT -> aboutAnswer(data.chatId.toString())
        }
    }

    private fun aboutAnswer(chatId: String) =
        BotAnswer(
            text = "Я Афина! Персональный помощник, пока нихуя не умею))).",
            chatId = chatId,
            newState = UserState.CURRENT
        )
}
