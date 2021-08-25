package duke.command;

public class CommandExit extends Command {

    public CommandExit() {
        this.commandName = "gubbai";
        this.description = "Exits the program";
    }

    @Override
    public void execute() {
        return;
    }
}
