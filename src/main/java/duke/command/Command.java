package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents the various types of commands that can be entered by the user.
 */
public abstract class Command {
    private String command;

    /**
     * Represents a constructor for the Command class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Represents Duke's response to various types of user commands.
     *
     * @param taskList TaskList where the tasks are stored.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.
     * @return String response to various types of commands.
     * @throws DukeException If there is an error.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;
}
