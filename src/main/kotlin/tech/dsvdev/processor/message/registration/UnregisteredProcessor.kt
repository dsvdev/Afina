package tech.dsvdev.processor.message.registration

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Message
import tech.dsvdev.model.BotAnswer
import tech.dsvdev.model.UserState
import tech.dsvdev.processor.MessageProcessor

@Component
class UnregisteredProcessor : MessageProcessor {
    override fun process(data: Message): BotAnswer {
        return BotAnswer(
            text = """
                Приветствую, я Афина!
                Кажется мы незнакомы?
                
                Как я могу как обращаться?
            """.trimIndent(),
            chatId = data.chatId.toString(),
            newState = UserState.NAME_INPUT
        )
    }
}