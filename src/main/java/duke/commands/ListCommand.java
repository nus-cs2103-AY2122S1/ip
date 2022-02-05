package duke.commands;

import duke.tasks.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {

    /**
     * Return a list String of all the tasks stored in Duke.
     *
     * @param tasks Current taskList.
     * @param ui User interface of Duke.
     * @return String output result of the list command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.toString();
    }
}
