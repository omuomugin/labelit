package view.command

import domain.model.Repository
import domain.model.Token
import presentation.runner.ShowCommandRunner
import view.output.OutPutBoundary

class ShowCommand : GitHubCommand(help = "show current set labels with target repo"), OutPutBoundary {
    override fun run() {
        val repoData = repo.split("/")

        val repository = Repository(ownerName = repoData[0], name = repoData[1])
        val token = Token(token = token)

        val runner = ShowCommandRunner(this, token, repository)
        echo(runner.run())
    }

    override fun outPutMessage(message: String) {
        echo(message)
    }
}