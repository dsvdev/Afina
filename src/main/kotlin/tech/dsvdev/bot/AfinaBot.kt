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
import tech.dsvdev.model.BotAnswer
import tech.dsvdev.serice.ProcessorService

@Component
class AfinaBot(
    @Value("\${bot.username}")
    private val botUsername: String,
    @Value("\${bot.token}")
    private val botToken: String,
    private val processorService: ProcessorService
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
        answer.execute()
    }

    private fun processCallbackQuery(callbackQuery: CallbackQuery) {
        TODO()
    }

    private fun BotAnswer.execute() {
        imageURL?.sendImage(chatId)
        text.sendMessage(chatId)
    }

    private fun String.sendMessage(chatId: String) {
        val message = SendMessage()
        message.text = this
        message.chatId = chatId
        execute(message)
    }

    private fun String.sendImage(chatId: String) {
        val image = SendPhoto()
        image.photo = InputFile(this)
        image.chatId = chatId
        execute(image)
    }
}
