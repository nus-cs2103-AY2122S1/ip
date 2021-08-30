package duke.commands;

import java.util.*;

import duke.tasks.Task;
import duke.utils.*;
/**
 * Represent a action to be executed.
 */
public abstract class Command {    

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}