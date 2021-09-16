package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage);
    //public abstract String execute(NotesList notes, Ui ui, Storage storage);
    public abstract Boolean isExit();
    //public abstract Boolean isTaskRelatedCommand();
}
