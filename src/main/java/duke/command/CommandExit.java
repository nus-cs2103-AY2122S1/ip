package duke.command;

import duke.TaskList;
import duke.ui.UserInterface;

public class CommandExit extends Command {
    public static final String EXIT_NAME = "exit";

    public CommandExit() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui) {
        ui.displayFarewell();
    }
}
