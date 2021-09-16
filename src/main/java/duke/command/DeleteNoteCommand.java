package duke.command;

import duke.Storage;
import duke.Ui;
import duke.notes.Note;
import duke.notes.NotesList;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

public class DeleteNoteCommand extends Command {
    String noteNumber;
    public DeleteNoteCommand(String command) {
        this.noteNumber = command;
    }

    @Override
    public String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage) {
        Integer number = Integer.valueOf(this.noteNumber);
        Note note = notes.getNote(number - 1);
        notes.getNotes().remove(number - 1);
        storage.rewriteFile(tasks.getTasks(), notes.getNotes());
        return ui.respondToDeleteNote(notes.getNotes(), note);
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
