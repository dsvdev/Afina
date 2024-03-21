package tech.dsvdev.processor

import org.telegram.telegrambots.meta.api.objects.Message

interface MessageProcessor : Processor<Message> {
}
