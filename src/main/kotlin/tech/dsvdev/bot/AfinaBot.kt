package tech.dsvdev.bot

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import tech.dsvdev.model.BotAnswer
import tech.dsvdev.model.UserState
import tech.dsvdev.service.ProcessorService
import tech.dsvdev.service.UserService

@Component
class AfinaBot(
    @Value("\${bot.username}")
    private val botUsername: String,
    @Value("\${bot.token}")
    private val botToken: String,
    private val processorService: ProcessorService,
    private val userService: UserService
) : TelegramLongPollingBot(botToken) {
    override fun getBotUsername() = botUsername

    override fun onUpdateReceived(update: Update?) {
        update ?: return
        if (update.hasMessage()) {
            processMessage(update.message)
        }
        if (update.hasCallbackQuery()) {
            processCallbackQuery(update.callbackQuery)
        }
    }

    private fun processMessage(message: Message) {
        val answer = processorService.getMessageProcessor(message).process(message)
        answer.execute(message.from.id)
    }

    private fun processCallbackQuery(callbackQuery: CallbackQuery) {
        TODO()
    }

    private fun BotAnswer.execute(userId: Long) {
        val user = userService.getById(userId)
        if (newState != UserState.CURRENT) {
            user.state = newState
            userService.save(user)
        }
        imageURL?.sendImage(chatId)
        text.sendMessage(chatId, user.state.keyboard)
    }

    private fun String.sendMessage(chatId: String, keyboard: ReplyKeyboard? = null) {
        val message = SendMessage()
        message.text = this
        message.chatId = chatId
        keyboard?.let { message.replyMarkup = it }
        execute(message)
    }

    private fun String.sendImage(chatId: String) {
        val image = SendPhoto()
        image.photo = InputFile(this)
        image.chatId = chatId
        execute(image)
    }
}
