package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Class to encapsulate a DoneCommand
 */
public class DoneCommand extends Command {
    public static final String INSTRUCTION_DONE = "done";
    protected int taskNumber;

    /**
     * Class constructor for DoneCommand Class specifying the task number
     */
    public DoneCommand(String taskNumber) throws DukeException {
        if (taskNumber.equals("")) {
            throw new DukeException("OOPS!!! The task number of done cannot be empty.");
        }
        this.taskNumber = Integer.parseInt(taskNumber);
    }

    /**
     * Executes the command
     *
     * @param tasks    the TaskList
     * @param ui       the Ui
     * @param storage  the data source
     *
     * @return         string stating the command result
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.markAsDone(taskNumber - 1);
        storage.update(taskNumber, task, "m");
        return "Nice! I've marked this task as done:\n" + "  [X] " + task.toString();
    }

    /**
     * Checks if the command is an ExitCommand
     *
     * @return           boolean stating if command is ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[" + INSTRUCTION_DONE + "] - " + taskNumber;
    }
}
