package presentation.runner

import domain.model.Label
import domain.model.Repository
import domain.model.Token
import domain.usecase.FetchLabelUseCase
import infra.query.GitHubLabelQueryServiceImpl

class ShowCommandRunner(private val token: Token, private val repository: Repository) : CommandRunner {
    override fun run(): String {
        val fetchLabelUseCase = FetchLabelUseCase(service = GitHubLabelQueryServiceImpl())
        val labels = fetchLabelUseCase.fetchLabels(repository, token)

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
}