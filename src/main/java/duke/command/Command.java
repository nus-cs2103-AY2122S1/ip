package duke.command;

import duke.DukeException;
import duke.TaskList;

public abstract class Command {

    public abstract void execute(TaskList taskList) throws DukeException;
    public abstract String getType();

}
