package duke.command;

import java.io.*;
import java.util.*;
import duke.util.UI;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws IOException;

    public boolean isExit() {
        return false;
    }
}
