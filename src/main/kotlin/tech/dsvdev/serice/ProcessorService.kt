package tech.dsvdev.serice

import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import tech.dsvdev.processor.CallbackProcessor
import tech.dsvdev.processor.MessageProcessor

interface ProcessorService {
    fun getMessageProcessor(message: Message) : MessageProcessor
    fun getCallbackProcessor(callbackQuery: CallbackQuery) : CallbackProcessor
}
