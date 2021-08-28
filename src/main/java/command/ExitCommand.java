package main.java.command;

import main.java.bot.TaskList;
import main.java.bot.UserInterface;

public class ExitCommand extends Command {

    public ExitCommand(String input) {
        super(input);
    }

    public void execute(TaskList list, UserInterface ui) {
        ui.showBye();
    }
}
