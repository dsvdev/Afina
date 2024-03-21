package tech.dsvdev.processor.message

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Message
import tech.dsvdev.model.BotAnswer
import tech.dsvdev.model.UserState
import tech.dsvdev.processor.MessageProcessor

@Component
class MainProcessor : MessageProcessor {
    override fun process(data: Message): BotAnswer {
        return BotAnswer(
            text = "MAIN",
            chatId = data.chatId.toString(),
            newState = UserState.MAIN
        )
    }
}