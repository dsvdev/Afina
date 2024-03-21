package tech.dsvdev.processor

import org.telegram.telegrambots.meta.api.objects.CallbackQuery

interface CallbackProcessor  : Processor<CallbackQuery> {
}
