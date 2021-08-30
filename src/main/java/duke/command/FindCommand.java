package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * @param ui the UI for the program
     * @param storage the storage utility for the program
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findMatchingTasks(this.keyword);
        ui.showMatchingTasks(matchingTasks);
    }

    /**
     * Method to determine if two instances of FindCommand are equal
     *
     * @param obj the object to be used for comparison
     * @return boolean indicating if the two FindCommand instances are equal
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof FindCommand && ((FindCommand) obj).keyword.equals(this.keyword);
    }
}
