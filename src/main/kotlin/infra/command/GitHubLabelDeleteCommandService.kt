package infra.command

import domain.model.Label

interface GitHubLabelDeleteCommandService {
    fun deleteLabels(owner: String, repoName: String, token: String, labels: List<Label>)
}