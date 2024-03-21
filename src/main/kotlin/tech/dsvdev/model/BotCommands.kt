package tech.dsvdev.model

enum class BotCommands(val command: String) {
    START("/start"),
    ABOUT("/about");
}

fun String.toBotCommand(): BotCommands  = BotCommands.entries.first { it.command == this }
