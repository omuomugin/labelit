package view.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.options.validate

abstract class GitHubCommand(help: String) : CliktCommand(help = help) {
    val token: String by option(
        help = "github token. you can set env as GIT_TOKEN too",
        envvar = "GIT_TOKEN"
    ).required().validate {
        require(it.isNotEmpty()) { "env 'GIT_TOKEN' should be set" }
    }

    val repo: String by option(
        help = "onwner/repo string to set target repository"
    ).required().validate {
        require(it.split("/").size == 2) { "repo should be 'owner/repo'" }
    }
}