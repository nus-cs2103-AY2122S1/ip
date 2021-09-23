package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

import java.util.ArrayList;

/**
 * The class to represent a command to find certain tasks.
 */
public class FindCommand extends Command {

    /** substring to find from the existing tasks */
    private String keyword;

    /** Constructor of FindCommand class */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Determines if the command is an exit command.
     *
     * @return whether it is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Carries out the command.
     *
     * @param tasks the list of tasks to be modified
     * @param storage the storage utility for the program
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findMatchingTasks(this.keyword);
        return tasks.showMatchingTasks(matchingTasks);
    }

    /**
     * Determines if two instances of FindCommand are equal.
     *
     * @param obj the object to be used for comparison
     * @return boolean indicating if the two FindCommand instances are equal
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof FindCommand && ((FindCommand) obj).keyword.equals(this.keyword);
    }
}
