package domain.event

sealed class Event {
    object GitHubLabelFetchSucceed : Event()
    object GitHubLabelAddSucceed : Event()
    object GitHubLabelDeleteSucceed : Event()
}