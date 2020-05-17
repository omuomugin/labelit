package domain.usecase

import domain.error.Error
import domain.model.Label
import domain.model.Repository
import domain.model.Token
import infra.query.GitHubLabelQueryService
import presentation.runner.ErrorHandler

class FetchLabelUseCase(
    private val errorHandler: ErrorHandler,
    private val service: GitHubLabelQueryService
) {
    fun fetchLabels(repository: Repository, token: Token): List<Label> {
        return try {
            service.fetchLabels(owner = repository.ownerName, repoName = repository.name, token = token.token)
        } catch (e: Exception) {
            errorHandler.onError(Error.GitHubLabelFetchFailed())
            emptyList()
        }
    }
}