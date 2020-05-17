package presentation.handler

import domain.error.Error

interface ErrorHandler {
    fun onError(error: Error)
}