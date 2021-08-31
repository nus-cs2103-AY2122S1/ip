package duke.command;

import duke.Ui;
import duke.Storage;
import duke.tasks.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
    public abstract Boolean isExit();
}
