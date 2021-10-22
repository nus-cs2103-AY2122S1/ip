package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Handles the list command which prints
 * the list of tasks stored by duke.Duke.
 */
public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks) {
        return Ui.printTasks(tasks);
    }
}
