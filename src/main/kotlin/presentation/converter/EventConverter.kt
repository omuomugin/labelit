package presentation.converter

import domain.event.Event

object EventConverter {
    fun convertEventToString(event: Event): String {
        return when (event) {
            Event.GitHubLabelFetchSucceed -> "[Event]: Label Fetch Succeed from GitHub"
            Event.GitHubLabelAddSucceed -> "[Event]: Label Add Succeed from GitHub"
            Event.GitHubLabelDeleteSucceed -> "[Event]: Label Delete Succeed from GitHub"
        }
    }
}