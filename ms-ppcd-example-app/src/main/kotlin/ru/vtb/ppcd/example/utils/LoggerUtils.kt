package org.example.ru.vtb.ppcd.example.utils

import java.util.logging.Logger

fun <T: Any> T.logger(): Logger {
    return Logger.getLogger(this.javaClass.name)
}