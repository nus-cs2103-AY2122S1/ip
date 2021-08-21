package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {
    protected boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    
    public boolean isExit() {
        return this.isExit;
    } 
}
