package view.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.options.validate

abstract class GitHubCommand(help: String) : CliktCommand(help = help) {
    val token: String by option(
        help = "github token. you can use env named GIT_TOKEN too",
        envvar = "GIT_TOKEN"
    ).required().validate {
        require(it.isNotEmpty()) {
            "--token should not be empty you can also set env 'GIT_TOKEN' for token"
        }
    }

    val repo: String by option(
        help = "owner/repo string to set target repository"
    ).required().validate {
        require(it.split("/").size == 2) { "--repo should be 'owner/repo'" }
    }
}