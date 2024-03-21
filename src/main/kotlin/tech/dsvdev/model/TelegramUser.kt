package tech.dsvdev.model

data class TelegramUser(
    val telegramId: Long,
    var state: UserState
)
