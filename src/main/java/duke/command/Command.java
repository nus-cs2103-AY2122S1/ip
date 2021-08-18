package duke.command;

import duke.exception.NoSuchTaskException;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws IOException, NoSuchTaskException;
    public abstract boolean isExit();
}
