package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Displays all of the user's tasks.
 */
public class ListCommand implements Command {
    public static final String COMMAND_IDENTIFIER = "list";

    /**
     * Returns a List command.
     *
     * @return List command;
     */
    public static Command create() {
        return new ListCommand();
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.showTaskList(tasks);
    }
}
