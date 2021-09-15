package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * Represents a command specified by user input.
 */
public abstract class Command {

    /**
     * 
     * @param tasks TaskList to access tasks and modify list.
     * @param ui To create response to user.
     * @param storage To write to duke.txt with each change.
     * @return String to be displayed to user on GUI.
     * @throws DukeException when user input does not match the specified formats.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
