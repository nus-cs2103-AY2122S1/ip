package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand implements Command {
    public static final String COMMAND_IDENTIFIER = "bye";

    public static Command create() {
        return new ExitCommand();
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
