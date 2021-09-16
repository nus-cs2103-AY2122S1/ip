package duke.commands;

import duke.tasks.TaskList;
import duke.util.Ui;

public class FalseCommand extends Command {

    /**
     * Returns an error String message as input command cannot be recognised.
     *
     * @param tasks Current taskList.
     * @param ui User interface of Duke.
     * @return Error String message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return "OOPS! Invalid command input! Please try again :)";
    }
}
