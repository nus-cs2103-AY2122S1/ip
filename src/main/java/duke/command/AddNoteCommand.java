package duke.command;

import duke.Ui;
import duke.Storage;
import duke.exceptions.NoteException;
import duke.notes.NotesList;
import duke.tasks.TaskList;
import duke.notes.Note;

/**
 * Adds a note to the task manager in response to the user's input.
 */
public class AddNoteCommand extends Command {
    private String command;

    /**
     * Instantiates an object of the AddNoteCommand class.
     *
     * @param command User's input.
     */
    public AddNoteCommand(String command) {
        this.command = command;
    }

    private String getTypeOfCommand() {
        return this.command.split(" ")[0];
    }

    private String getNoteDescription() {
        String noteDescription = this.command.split(" ", 2)[1];
        return noteDescription;
    }

    /**
     * Executes the action of adding a note to the task manager Peppa.
     *
     * @param tasks List of tasks stored in the task manager.
     * @param notes List of notes stored in the task manager.
     * @param ui User interface of the task manager.
     * @param storage Hard disk containing all the tasks and notes of the task manager.
     * @return Message to be printed on the user interface to notify the user of the outcome of the input entered.
     */
    @Override
    public String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage) {
        try {
            String typeOfCommand = getTypeOfCommand();
            assert typeOfCommand != "note";
            String descriptionOfNote = getNoteDescription();
            Note note = new Note(descriptionOfNote);
            notes.addNote(note);
            storage.appendToFile(note);
            return ui.respondToNote(notes.getNotes(), note);
        } catch(NoteException e) {
            return ui.showError(e.getMessage());
        }
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
