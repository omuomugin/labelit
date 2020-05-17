package presentation.handler

import domain.event.Event

interface EventHandler {
    fun onEvent(event: Event)
}