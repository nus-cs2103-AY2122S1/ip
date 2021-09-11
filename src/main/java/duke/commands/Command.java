package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.TaskList;

public abstract class Command {
    public abstract TaskList execute(TaskList taskList) throws DukeException;
}
