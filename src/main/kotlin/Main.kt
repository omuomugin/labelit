import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import view.command.ShowCommand
import view.command.UpdateCommand

class Label : CliktCommand() {
    override fun run() = Unit
}

fun main(args: Array<String>) = Label()
    .subcommands(ShowCommand(), UpdateCommand())
    .main(args)