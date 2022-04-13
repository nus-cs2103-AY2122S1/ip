package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;

/**
 * Represents a command that is executed in response to a user's input.
 */
public abstract class Command {
    /**
     * Executes a command in response to the user's input.
     *
     * @param tasks List of tasks stored in the task manager.
     * @param notes List of notes stored in the task manager.
     * @param ui User interface of the task manager.
     * @param storage Hard disk containing all the tasks and notes of the task manager.
     * @return Message to be printed on the user interface to notify the user of the outcome of the input entered.
     */
    public abstract String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage);

    /**
     * Returns a boolean value indicating if user wants to exit the task manager.
     *
     * @return Boolean value.
     */
    public abstract Boolean isExit();
}
