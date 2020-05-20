package infra.command

import domain.model.Label

interface GitHubLabelUpdateCommandService {
    fun updateLabels(owner: String, repoName: String, token: String, labels: List<Label>)
}