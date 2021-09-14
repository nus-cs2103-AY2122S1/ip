package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;

public class ListNoteCommand extends Command{
    String command;
    public ListNoteCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "";
    }

    @Override
    public String execute(NotesList notes, Ui ui, Storage storage) {
        return ui.respondToNotesList(notes.getNotes());
    }

    @Override
    public Boolean isTaskRelatedCommand() {
        return false;
    };

    @Override
    public Boolean isExit() {
        return false;
    }
}
