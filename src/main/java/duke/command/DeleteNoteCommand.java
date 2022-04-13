package duke.command;

import duke.Storage;
import duke.Ui;
import duke.notes.Note;
import duke.notes.NotesList;
import duke.tasks.TaskList;

/**
 * Deletes a note from the task manager in response to the user's input.
 */
public class DeleteNoteCommand extends Command {
    private String noteNumber;

    /**
     * Instantiates an object of the DeleteNoteCommand class.
     *
     * @param command User's input.
     */
    public DeleteNoteCommand(String command) {
        this.noteNumber = command;
    }

    /**
     * Executes the action of deleting a note from the task manager Peppa.
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
            Integer number = Integer.valueOf(this.noteNumber);
            if(number <= 0 || number > notes.getNumberOfNotes()) {
                return ui.showError("You have entered a index that does not correspond to any note.");
            }
            Note note = notes.getNote(number - 1);
            notes.getNotes().remove(number - 1);
            storage.rewriteFile(tasks.getTasks(), notes.getNotes());
            return ui.respondToDeleteNote(notes.getNotes(), note);
        }  catch(NumberFormatException e) {
            return ui.showError("You have entered an invalid input that is not a number.");
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
