package presentation.runner

import domain.error.Error
import domain.event.Event
import domain.model.Label
import domain.model.Repository
import domain.model.Token
import domain.usecase.FetchLabelUseCase
import infra.query.GitHubLabelQueryServiceImpl
import presentation.converter.ErrorConverter
import presentation.converter.EventConverter
import presentation.handler.ErrorHandler
import presentation.handler.EventHandler
import view.output.OutPutBoundary

class ShowCommandRunner(
    private val outPutBoundary: OutPutBoundary,
    private val token: Token,
    private val repository: Repository
) : CommandRunner, ErrorHandler, EventHandler {

    private val errorList: MutableList<Error> = mutableListOf()

    override fun run(): String {
        val fetchLabelUseCase = FetchLabelUseCase(
            service = GitHubLabelQueryServiceImpl(),
            errorHandler = this,
            eventHandler = this
        )
        val labels = fetchLabelUseCase.fetchLabels(repository, token)

        if (errorList.isNotEmpty()) {
            return "Error Occurred : failed in some place"
        }

        return formatToPrettyString(labels)
    }

    private fun formatToPrettyString(labels: List<Label>): String {
        var prettyStr = "=================================\n"
        prettyStr += "labels:"
        labels.forEach { label ->
            prettyStr += """
    - name: "${label.name}"
      color: "${label.color}""""
            if (label.description.isNotEmpty()) {
                prettyStr += """
      description: "${label.description}""""
            }
        }
        prettyStr += "\n================================="

        return prettyStr
    }

    override fun onError(error: Error) {
        errorList.add(error)
        outPutBoundary.outPutMessage(ErrorConverter.convertErrorToString(error))
    }

    override fun onEvent(event: Event) {
        outPutBoundary.outPutMessage(EventConverter.convertEventToString(event))
    }
}