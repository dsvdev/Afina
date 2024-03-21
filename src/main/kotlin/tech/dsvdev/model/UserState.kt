package tech.dsvdev.model

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import tech.dsvdev.utils.buildKeyboard

enum class UserState(val keyboard: ReplyKeyboard? = null) {
    CURRENT,
    UNREGISTERED,
    NAME_INPUT,
    NAME_APPROVE(nameApproveKeyboard),
    MAIN(mainKeyboard),
    FINANCE,
}

private val nameApproveKeyboard = buildKeyboard(
    listOf(
        "Да", "Нет"
    )
)

private val mainKeyboard = buildKeyboard(
    listOf(
        "Пункт 1", "Пунтк 2", "Пункт 3"
    )
)


