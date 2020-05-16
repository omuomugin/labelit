package presentation.runner

import presentation.model.Config

class UpdateCommandRunner(private val config: Config) : CommandRunner {
    override fun run(): String {
        return config.toString()
    }
}