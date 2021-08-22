package duke.command;

import duke.*;
import duke.tasks.*;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, FileController fc);

    public boolean isExit() {
        return false;
    }
}
