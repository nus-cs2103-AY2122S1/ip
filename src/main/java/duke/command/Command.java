package duke.command;

import duke.Storable;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storable storage);
}
