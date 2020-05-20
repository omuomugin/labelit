package presentation.converter

import domain.event.Event

object EventConverter {
    fun convertEventToString(event: Event): String {
        return when (event) {
            is Event.GitHubLabelFetchSucceed -> "[Event]: Label Fetch Succeed from GitHub"
            is Event.GitHubLabelAddSucceed -> "[Event]: Label Add Succeed from GitHub"
            is Event.GitHubLabelDeleteSucceed -> "[Event]: Label Delete Succeed from GitHub"
            is Event.GitHubLabelUpdateSucceed -> "[Event]: Label Update Succeed from GitHub"
            is Event.LabelUpdate -> {
                val deleteLabelNames = event.deleteLabels.map { it.name }
                val updateLabelNames = event.updateLabels.map { it.name }
                val newLabelNames = event.newLabels.map { it.name }

                """
                [Event]: deleting { $deleteLabelNames }
                [Event]: updating { $updateLabelNames }
                [Event]: adding { $newLabelNames }
                """.trimIndent()
            }
        }
    }
}