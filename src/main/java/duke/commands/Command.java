package duke.commands;

import duke.exceptions.OutOfTaskListRangeException;
import duke.tasks.TaskList;
import duke.utils.Storage;

public abstract class Command {

    /**
     * Executes the command generated from user input
     *
     * @param tasks current list of tasks
     * @param storage Storage responsible for accessing and storing Duke data
     * @return the string representation of the command
     */
    public abstract String execute(TaskList tasks, Storage storage) throws OutOfTaskListRangeException;
}
