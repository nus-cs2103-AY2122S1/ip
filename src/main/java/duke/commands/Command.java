package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

public abstract class Command {
    
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
