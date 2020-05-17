package presentation.runner

import domain.error.Error
import domain.model.Label
import domain.model.Repository
import domain.model.Token
import domain.usecase.FetchLabelUseCase
import infra.query.GitHubLabelQueryServiceImpl
import presentation.converter.ErrorConverter
import view.command.OutPutBoundary

class ShowCommandRunner(
    private val outPutBoundary: OutPutBoundary,
    private val token: Token,
    private val repository: Repository
) : CommandRunner, ErrorHandler {

    private val errorList: MutableList<Error> = mutableListOf()

    override fun run(): String {
        val fetchLabelUseCase = FetchLabelUseCase(service = GitHubLabelQueryServiceImpl(), errorHandler = this)
        val labels = fetchLabelUseCase.fetchLabels(repository, token)

        if (errorList.isNotEmpty()) {
            return "Error Occurred : failed in some place"
        }

        return formatToPrettyString(labels)
    }

    private fun formatToPrettyString(labels: List<Label>): String {
        var prettyStr = ""
        labels.forEach { label ->
            prettyStr += """
            ${label.name} (${label.description}) : #${label.color}
        """.trimIndent() + "\n"
        }

        return prettyStr
    }

    override fun onError(error: Error) {
        errorList.add(error)
        outPutBoundary.outPutMessage(ErrorConverter.convertErrorToString(error))
    }
}