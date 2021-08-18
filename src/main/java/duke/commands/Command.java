package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws IOException, NoSuchTaskException;
    public abstract boolean isExit();
}
