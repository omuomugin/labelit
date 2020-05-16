package view.command

import com.github.ajalt.clikt.core.CliktCommand
import presentation.runner.ShowCommandRunner

class ShowCommand : CliktCommand(help = "Show Commands") {
    override fun run() {
        val runner = ShowCommandRunner()
        echo(runner.run())
    }
}