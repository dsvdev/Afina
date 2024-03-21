package tech.dsvdev.bot

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class AfinaBot(
    @Value("\${bot.username}")
    private val botUsername: String,
    @Value("\${bot.token}")
    private val botToken: String
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
        val chatId = message.chatId.toString()
        val answer = SendMessage()
        answer.text = "Привет! Я Афина!"
        answer.chatId = chatId
        execute(answer)
    }

    private fun processCallbackQuery(callbackQuery: CallbackQuery) {
        TODO()
    }
}