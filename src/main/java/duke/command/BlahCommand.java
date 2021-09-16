package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;

/**
 * Responds to a user's random or invalid input.
 */
public class BlahCommand extends Command {
    private String command;

    /**
     * Instantiates an object of the AddNoteCommand class.
     *
     * @param command User's input.
     */
    public BlahCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the action of responding to a user's random input.
     *
     * @param tasks List of tasks stored in the task manager.
     * @param notes List of notes stored in the task manager.
     * @param ui User interface of the task manager.
     * @param storage Hard disk containing all the tasks and notes of the task manager.
     * @return Message to be printed on the user interface to notify the user of the outcome of the input entered.
     */
    @Override
    public String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage) {
        return ui.respondToBlah();
    }

    /**
     * Returns a boolean value indicating if user wants to exit the task manager.
     *
     * @return Boolean value.
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
