package duke.commands;

import java.util.*;

import duke.tasks.Task;
import duke.utils.*;

public abstract class Command {    

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}