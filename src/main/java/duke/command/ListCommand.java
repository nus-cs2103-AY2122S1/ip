package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * The class to represent a command to list tasks.
 */
public class ListCommand extends Command {

    /**
     * Method to determine if the command is an exit command
     *
     * @return whether it is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Method to carry out the command
     *
     * @param tasks the list of tasks to be modified
     * @param storage the storage utility for the program
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.showAllTasks();
    }

    /**
     * Method to determine if the object is a ListCommand
     *
     * @param obj the object to be used for comparison
     * @return boolean indicating if the object is a ListCommand
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
