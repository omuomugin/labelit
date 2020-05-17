package domain.usecase

import domain.error.Error
import domain.event.Event
import domain.model.Label
import domain.model.Repository
import domain.model.Token
import infra.query.GitHubLabelQueryService
import presentation.runner.ErrorHandler
import presentation.runner.EventHandler

class FetchLabelUseCase(
    private val eventHandler: EventHandler,
    private val errorHandler: ErrorHandler,
    private val service: GitHubLabelQueryService
) {
    fun fetchLabels(repository: Repository, token: Token): List<Label> {
        return try {
            val labels = service.fetchLabels(
                owner = repository.ownerName,
                repoName = repository.name,
                token = token.token
            )
            eventHandler.onEvent(Event.GitHubLabelFetchSucceed)
            labels
        } catch (e: Exception) {
            errorHandler.onError(Error.GitHubLabelFetchFailed)
            emptyList()
        }
    }
}