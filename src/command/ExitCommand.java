package command;

import bot.TaskList;
import bot.UserInterface;

public class ExitCommand extends Command {

    public ExitCommand(String input) {
        super(input);
    }

    public void execute(TaskList list, UserInterface ui) {
        ui.showBye();
    }
}
