package view.command

import domain.model.Repository
import domain.model.Token
import presentation.runner.ShowCommandRunner

class ShowCommand : GitHubCommand(help = "Show Commands") {
    override fun run() {
        val repoData = repo.split("/")

        val repository = Repository(ownerName = repoData[0], name = repoData[1])
        val token = Token(token = token)

        val runner = ShowCommandRunner(token, repository)
        echo(runner.run())
    }
}