package duke.command;

import duke.task.TaskList;

abstract public class DukeCommand {
    abstract public void execute(TaskList tl);
    abstract public boolean isExit();
}
