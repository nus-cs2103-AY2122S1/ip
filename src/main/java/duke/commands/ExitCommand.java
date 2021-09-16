package duke.commands;

import duke.tasks.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {

    /**
     * Ends Duke and closes window.
     *
     * @param tasks Current taskList.
     * @param ui User interface of Duke.
     * @return String output result of the exit command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return "Bye. Hope to see you again soon!";
    }
}
