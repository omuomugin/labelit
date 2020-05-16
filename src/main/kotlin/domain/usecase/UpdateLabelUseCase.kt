package domain.usecase

import domain.model.Label
import domain.model.Repository
import domain.model.Token
import infra.command.GitHubLabelAddCommandService
import infra.command.GitHubLabelDeleteCommandService
import infra.query.GitHubLabelQueryService

class UpdateLabelUseCase(
    private val addService: GitHubLabelAddCommandService,
    private val deleteService: GitHubLabelDeleteCommandService,
    private val queryService: GitHubLabelQueryService
) {
    fun updateLabels(repository: Repository, token: Token, labels: List<Label>) {
        val currentLabels = queryService.fetchLabels(
            owner = repository.ownerName,
            repoName = repository.name,
            token = token.token
        )

        deleteService.deleteLabels(
            owner = repository.ownerName,
            repoName = repository.name,
            token = token.token,
            labels = currentLabels
        )

        addService.addLabels(
            owner = repository.ownerName,
            repoName = repository.name,
            token = token.token,
            labels = labels
        )
    }
}