package domain.event

import domain.model.Label

sealed class Event {
    object GitHubLabelFetchSucceed : Event()
    object GitHubLabelAddSucceed : Event()
    object GitHubLabelDeleteSucceed : Event()
    object GitHubLabelUpdateSucceed : Event()
    class LabelUpdate(
        val updateLabels: List<Label>,
        val newLabels: List<Label>,
        val deleteLabels: List<Label>
    ) : Event()
}