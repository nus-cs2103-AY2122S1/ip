package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;

/**
 * Lists the notes in the task manager application, Peppa.
 */
public class ListNoteCommand extends Command{
    private String command;

    /**
     * Instantiates an object of the ListNoteCommand class.
     *
     * @param command User's input.
     */
    public ListNoteCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the action of listing all the notes in the task manager application.
     *
     * @param tasks List of tasks stored in the task manager.
     * @param notes List of notes stored in the task manager.
     * @param ui User interface of the task manager.
     * @param storage Hard disk containing all the tasks and notes of the task manager.
     * @return Message to be printed on the user interface to notify the user of the outcome of the input entered.
     */
    @Override
    public String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage) {
        return ui.respondToNotesList(notes.getNotes());
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
