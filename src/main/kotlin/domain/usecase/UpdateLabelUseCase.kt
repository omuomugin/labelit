package domain.usecase

import domain.error.Error
import domain.model.Label
import domain.model.Repository
import domain.model.Token
import infra.command.GitHubLabelAddCommandService
import infra.command.GitHubLabelDeleteCommandService
import infra.query.GitHubLabelQueryService
import presentation.runner.ErrorHandler

class UpdateLabelUseCase(
    private val errorHandler: ErrorHandler,
    private val addService: GitHubLabelAddCommandService,
    private val deleteService: GitHubLabelDeleteCommandService,
    private val queryService: GitHubLabelQueryService
) {
    fun updateLabels(repository: Repository, token: Token, labels: List<Label>) {
        val currentLabels: List<Label>
        try {
            currentLabels = queryService.fetchLabels(
                owner = repository.ownerName,
                repoName = repository.name,
                token = token.token
            )
        } catch (e: Exception) {
            errorHandler.onError(Error.GitHubLabelFetchFailed())
            return
        }

        try {
            deleteService.deleteLabels(
                owner = repository.ownerName,
                repoName = repository.name,
                token = token.token,
                labels = currentLabels
            )
        } catch (e: Exception) {
            errorHandler.onError(Error.GitHubLabelDeleteFailed())
            return
        }

        try {
            addService.addLabels(
                owner = repository.ownerName,
                repoName = repository.name,
                token = token.token,
                labels = labels
            )
        } catch (e: Exception) {
            errorHandler.onError(Error.GitHubLabelAddFailed())
            return
        }
    }
}