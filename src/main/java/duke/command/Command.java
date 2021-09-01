package duke.command;

import duke.exception.InvalidInputException;
import duke.util.Storage;
import duke.util.Ui;
import duke.util.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException;
    public abstract boolean isExit();

}
