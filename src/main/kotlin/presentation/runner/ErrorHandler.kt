package presentation.runner

import domain.error.Error

interface ErrorHandler {
    fun onError(error: Error)
}