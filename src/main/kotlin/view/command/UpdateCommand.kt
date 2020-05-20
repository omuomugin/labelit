package view.command

import com.charleskorn.kaml.Yaml
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import domain.model.Repository
import domain.model.Token
import presentation.converter.ConfigConverter
import presentation.model.Config
import presentation.runner.UpdateCommandRunner
import view.output.OutPutBoundary

class UpdateCommand : GitHubCommand(
    help = "Update if name already exists, Add if not and Delete if not given in yaml but already set"
), OutPutBoundary {
    val config by option().file(mustExist = true).required()

    override fun run() {
        val repository = repo.split("/").let {
            Repository(ownerName = it[0], name = it[1])
        }

        val token = Token(token = token)

        val config = Yaml.default.parse(Config.serializer(), config.readText()).let {
            ConfigConverter.convert(it)
        }

        val runner = UpdateCommandRunner(this, token, repository, config)

        echo(runner.run())
    }

    override fun outPutMessage(message: String) {
        echo(message)
    }
}
