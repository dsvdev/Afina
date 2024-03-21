package tech.dsvdev.model

data class BotAnswer(
    val imageURL: String? = null,
    val text: String,
    val chatId: String,
    val newState: UserState
    )