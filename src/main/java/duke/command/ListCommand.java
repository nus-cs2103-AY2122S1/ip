package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand implements Command {
    public static final String COMMAND_IDENTIFIER = "list";

    public static Command create() {
        return new ListCommand();
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
