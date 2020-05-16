package infra.query

import domain.model.Label

interface GitHubLabelQueryService {
    fun fetchLabels(owner: String, repoName: String, token: String): List<Label>
}