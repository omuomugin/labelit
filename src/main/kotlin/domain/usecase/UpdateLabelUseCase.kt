package domain.usecase

import domain.error.Error
import domain.event.Event
import domain.model.Label
import domain.model.Repository
import domain.model.Token
import infra.command.GitHubLabelAddCommandService
import infra.command.GitHubLabelDeleteCommandService
import infra.command.GitHubLabelUpdateCommandService
import infra.query.GitHubLabelQueryService
import presentation.handler.ErrorHandler
import presentation.handler.EventHandler

class UpdateLabelUseCase(
    private val eventHandler: EventHandler,
    private val errorHandler: ErrorHandler,
    private val addService: GitHubLabelAddCommandService,
    private val deleteService: GitHubLabelDeleteCommandService,
    private val updateService: GitHubLabelUpdateCommandService,
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
            eventHandler.onEvent(Event.GitHubLabelFetchSucceed)
        } catch (e: Exception) {
            errorHandler.onError(Error.GitHubLabelFetchFailed)
            return
        }


        val (updateLabels, newLabels, deleteLabels) = classifyLabels(currentLabels, labels)
        eventHandler.onEvent(
            Event.LabelUpdate(
                updateLabels = updateLabels,
                newLabels = newLabels,
                deleteLabels = deleteLabels
            )
        )

        try {
            deleteService.deleteLabels(
                owner = repository.ownerName,
                repoName = repository.name,
                token = token.token,
                labels = deleteLabels
            )
            eventHandler.onEvent(Event.GitHubLabelDeleteSucceed)
        } catch (e: Exception) {
            errorHandler.onError(Error.GitHubLabelDeleteFailed)
            return
        }

        try {
            addService.addLabels(
                owner = repository.ownerName,
                repoName = repository.name,
                token = token.token,
                labels = newLabels
            )
            eventHandler.onEvent(Event.GitHubLabelAddSucceed)
        } catch (e: Exception) {
            errorHandler.onError(Error.GitHubLabelAddFailed)
            return
        }

        try {
            updateService.updateLabels(
                owner = repository.ownerName,
                repoName = repository.name,
                token = token.token,
                labels = updateLabels
            )
            eventHandler.onEvent(Event.GitHubLabelUpdateSucceed)
        } catch (e: Exception) {
            errorHandler.onError(Error.GitHubLabelUpdateFailed)
            return
        }
    }

    /**
     * Classify lists into each target lists
     *
     * @param currentLabels current labels which is set to GitHub
     * @param targetLabels labels from yaml
     * @return Triple of (updateLabels, newLabels, deleteLabels)
     */
    private fun classifyLabels(
        currentLabels: List<Label>,
        targetLabels: List<Label>
    ): Triple<List<Label>, List<Label>, List<Label>> {
        val currentLabelMap: Map<String, Label> = currentLabels.map { it.name to it }.toMap()
        val targetLabelMap: Map<String, Label> = targetLabels.map { it.name to it }.toMap()

        val updateLabels: List<Label> =
            targetLabelMap.filter { currentLabelMap.containsKey(it.key) }.map { it.value }

        val newLabels: List<Label> =
            targetLabelMap.filter { !currentLabelMap.containsKey(it.key) }.map { it.value }

        val deleteLabels: List<Label> =
            currentLabelMap.filter { !targetLabelMap.containsKey(it.key) }.map { it.value }

        return Triple(updateLabels, newLabels, deleteLabels)
    }
}