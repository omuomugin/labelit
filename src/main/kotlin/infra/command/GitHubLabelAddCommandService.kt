package infra.command

import domain.model.Label

interface GitHubLabelAddCommandService {
    fun addLabels(owner: String, repoName: String, token: String, labels: List<Label>)
}