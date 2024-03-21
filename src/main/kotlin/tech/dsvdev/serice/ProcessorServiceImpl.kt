package tech.dsvdev.serice

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import tech.dsvdev.model.BotCommands
import tech.dsvdev.model.UserState
import tech.dsvdev.processor.CallbackProcessor
import tech.dsvdev.processor.MessageProcessor
import tech.dsvdev.processor.message.CommandProcessor

@Service
class ProcessorServiceImpl(
    private val userService: UserService,
    private val commandProcessor: CommandProcessor
) : ProcessorService {
    override fun getMessageProcessor(message: Message): MessageProcessor {
        if (message.text in BotCommands.entries.map { it.command }) {
            return commandProcessor
        }
        val state = userService.getById(message.from.id).state
        when (state) {
            UserState.UNTEGISTERED -> TODO()
            UserState.MAIN -> TODO()
            UserState.FINANCE -> TODO()
        }
    }

    override fun getCallbackProcessor(callbackQuery: CallbackQuery): CallbackProcessor {
        TODO("Not yet implemented")
    }
}
