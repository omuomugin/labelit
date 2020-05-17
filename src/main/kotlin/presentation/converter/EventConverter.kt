package presentation.converter

import domain.event.Event

object EventConverter {
    fun convertEventToString(event: Event): String {
        return when (event) {
            is Event.GitHubLabelFetchSucceed -> "[Event]: Label Fetch Succeed from GitHub"
            is Event.GitHubLabelAddSucceed -> "[Event]: Label Add Succeed from GitHub"
            is Event.GitHubLabelDeleteSucceed -> "[Event]: Label Delete Succeed from GitHub"
        }
    }
}