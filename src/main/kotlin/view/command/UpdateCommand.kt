package view.command

import com.charleskorn.kaml.Yaml
import com.github.ajalt.clikt.core.CliktCommand
import presentation.model.Config
import presentation.runner.UpdateCommandRunner

class UpdateCommand : CliktCommand() {
    override fun run() {

        // TODO: read from yaml file
        val input = """labels:
  - name: bug
    color: fc2929
    description: this is bug
  - name: help wanted
    color: 000000
  - name: fix
    color: cccccc
  - name: notes
    color: fbca04""".trimIndent()

        val result = Yaml.default.parse(Config.serializer(), input)

        echo(UpdateCommandRunner(result).run())
    }

}