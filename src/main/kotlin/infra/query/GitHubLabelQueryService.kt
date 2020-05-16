package infra.query

import domain.model.Label

interface GitHubLabelQueryService {
    fun fetchLabels(): List<Label>
}