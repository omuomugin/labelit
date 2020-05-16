package presentation.runner

import domain.model.Config
import domain.model.Repository
import domain.model.Token
import domain.usecase.UpdateLabelUseCase
import infra.command.GitHubLabelAddCommandServiceImpl
import infra.command.GitHubLabelDeleteCommandServiceImpl
import infra.query.GitHubLabelQueryServiceImpl

class UpdateCommandRunner(
    private val token: Token,
    private val repository: Repository,
    private val config: Config
) : CommandRunner {
    override fun run(): String {
        val updateLabelUseCase = UpdateLabelUseCase(
            addService = GitHubLabelAddCommandServiceImpl(),
            deleteService = GitHubLabelDeleteCommandServiceImpl(),
            queryService = GitHubLabelQueryServiceImpl()
        )

        updateLabelUseCase.updateLabels(repository, token, config.labels)

        return "Success!!"
    }
}