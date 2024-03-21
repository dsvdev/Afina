package tech.dsvdev.processor

import tech.dsvdev.model.BotAnswer

interface Processor<T> {
    fun process(data: T): BotAnswer
}
