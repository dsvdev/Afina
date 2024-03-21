package tech.dsvdev.processor.message

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Message
import tech.dsvdev.model.BotAnswer
import tech.dsvdev.model.BotCommands
import tech.dsvdev.model.toBotCommand
import tech.dsvdev.processor.MessageProcessor

@Component
class CommandProcessor : MessageProcessor {
    override fun process(data: Message): BotAnswer {
        if (data.text !in BotCommands.entries.map { it.command }) throw IllegalArgumentException()

        return when (data.text.toBotCommand()) {
            BotCommands.START -> startAnswer(data.chatId.toString())
            BotCommands.ABOUT -> aboutAnswer(data.chatId.toString())
        }
    }

    private fun startAnswer(chatId: String) =
        BotAnswer(
            text = "Привет! Я Афина! Я помогу тебе сделать выбор в мире IT. Чтобы узнать больше, напиши /about",
            chatId = chatId
        )

    private fun aboutAnswer(chatId: String) =
        BotAnswer(
            text = "Я Афина! Я помогу тебе сделать выбор в мире IT. Я умею отвечать на вопросы, которые ты задаешь мне.",
            chatId = chatId
        )
}
