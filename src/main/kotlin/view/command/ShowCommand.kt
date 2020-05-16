package view.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import domain.model.Repository
import domain.model.Token
import presentation.runner.ShowCommandRunner

class ShowCommand : CliktCommand(help = "Show Commands") {
    val token: String by option(
        help = "github token. you can set env as GIT_TOKEN too",
        envvar = "GIT_TOKEN"
    ).required()

    val repo: String by option(
        help = "onwner/repo string to set target repository"
    ).required()

    override fun run() {
        require(token.isNotEmpty()) { echo("env 'GIT_TOKEN' should be set") }

        val repoData = repo.split("/")
        require(repoData.size == 2) { echo("repo should be 'owner/repo'") }

        val repository = Repository(ownerName = repoData[0], name = repoData[1])
        val token = Token(token = token)

        val runner = ShowCommandRunner(token, repository)
        echo(runner.run())
    }
}