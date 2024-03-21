package tech.dsvdev.model

enum class BotCommands(val command: String) {
    ABOUT("/about");
}

fun String.toBotCommand(): BotCommands  = BotCommands.entries.first { it.command == this }
