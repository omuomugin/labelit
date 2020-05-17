package presentation.runner

import domain.error.Error
import domain.model.Config
import domain.model.Repository
import domain.model.Token
import domain.usecase.UpdateLabelUseCase
import infra.command.GitHubLabelAddCommandServiceImpl
import infra.command.GitHubLabelDeleteCommandServiceImpl
import infra.query.GitHubLabelQueryServiceImpl
import presentation.converter.ErrorConverter
import view.command.OutPutBoundary

class UpdateCommandRunner(
    private val outPutBoundary: OutPutBoundary,
    private val token: Token,
    private val repository: Repository,
    private val config: Config
) : CommandRunner, ErrorHandler {

    private val errorList: MutableList<Error> = mutableListOf()

    override fun run(): String {
        val updateLabelUseCase = UpdateLabelUseCase(
            errorHandler = this,
            addService = GitHubLabelAddCommandServiceImpl(),
            deleteService = GitHubLabelDeleteCommandServiceImpl(),
            queryService = GitHubLabelQueryServiceImpl()
        )

        updateLabelUseCase.updateLabels(repository, token, config.labels)

        if (errorList.isNotEmpty()) {
            return "Error Occurred : failed in some place"
        }

        return "Success : see GitHub labels so that your labels are updated"
    }

    override fun onError(error: Error) {
        errorList.add(error)
        outPutBoundary.outPutMessage(ErrorConverter.convertErrorToString(error))
    }
}