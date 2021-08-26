package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * A Command class encapsulates the instructions for Duke to execute
 */
public abstract class Command {

    /**
     * executes the command on the specified tasklist
     *
     * @param taskList tasklist to be operated on
     * @throws DukeException
     */
    public abstract void execute(TaskList taskList) throws DukeException;

    /**
     * returns the type of command
     *
     * @return type of command
     */
    public abstract String getType();

}
