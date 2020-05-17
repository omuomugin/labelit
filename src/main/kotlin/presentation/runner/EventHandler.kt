package presentation.runner

import domain.event.Event

interface EventHandler {
    fun onEvent(event: Event)
}