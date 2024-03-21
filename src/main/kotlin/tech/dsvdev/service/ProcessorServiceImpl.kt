package tech.dsvdev.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import tech.dsvdev.model.BotCommands
import tech.dsvdev.model.UserState
import tech.dsvdev.processor.CallbackProcessor
import tech.dsvdev.processor.MessageProcessor
import tech.dsvdev.processor.message.CommandProcessor
import tech.dsvdev.processor.message.MainProcessor
import tech.dsvdev.processor.message.registration.NameApproveProcessor
import tech.dsvdev.processor.message.registration.NameInputProcessor
import tech.dsvdev.processor.message.registration.UnregisteredProcessor

@Service
class ProcessorServiceImpl(
    private val userService: UserService,
    private val commandProcessor: CommandProcessor,
    private val unregisteredProcessor: UnregisteredProcessor,
    private val nameApproveProcessor: NameApproveProcessor,
    private val nameInputProcessor: NameInputProcessor,
    private val mainProcessor: MainProcessor

) : ProcessorService {
    override fun getMessageProcessor(message: Message): MessageProcessor {
        if (message.text in BotCommands.entries.map { it.command }) {
            return commandProcessor
        }
        val state = userService.getById(message.from.id).state
        return when (state) {
            UserState.UNREGISTERED -> unregisteredProcessor
            UserState.MAIN -> mainProcessor
            UserState.FINANCE -> TODO()
            UserState.NAME_INPUT -> nameInputProcessor
            UserState.NAME_APPROVE -> nameApproveProcessor
            UserState.CURRENT -> throw IllegalStateException("Пользователь не может находиться в техническом состоянии CURRENT")
        }
    }

    override fun getCallbackProcessor(callbackQuery: CallbackQuery): CallbackProcessor {
        TODO("Not yet implemented")
    }
}
