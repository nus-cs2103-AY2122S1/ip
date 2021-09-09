package duke.command;

import java.io.IOException;

import duke.Parser;
import duke.TaskList;
import duke.exception.DukeException;
import duke.storage.TaskStorage;
import duke.ui.Ui;

public abstract class Command {

    /**
     * Retrieves the task number as an integer from the task number string,
     * that is within the boundaries of the task list size.
     *
     * @param taskNumberString The string containing the task number.
     * @return The integer parsed from the task number string.
     * @throws DukeException When the task number string is not an integer;
     *                       when the task number integer parsed is out of range
     *                       of the size of the task lists.
     */
    public int retrieveTaskNumber(String taskNumberString, TaskList taskList) throws DukeException {
        int taskNumber = Parser.parseToInt(taskNumberString);

        if (taskNumber < 1 || taskNumber > taskList.getSize()) {
            throw new DukeException("Oops!!! The task number provided is not valid.");
        }
        return taskNumber;
    }

    /**
     * Executes the command.
     *
     * @param taskList The user's list of tasks.
     * @param taskStorage The user's storage of tasks in the hard disk.
     * @param ui The user interface to handle responses by Duke.
     */
    public abstract void execute(TaskList taskList, Ui ui, TaskStorage taskStorage)
            throws DukeException, IOException;
}
