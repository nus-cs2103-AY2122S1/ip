package duke.command;

import duke.task.Task;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class DeleteCommand extends Command {
    protected int taskNumber;
    public static final String INSTRUCTION = "delete";

    /**
     * Class constructor for DeleteCommand Class specifying the task number
     */
    public DeleteCommand(String taskNumber) throws DukeException {
        if (taskNumber.equals("")) {
            throw new DukeException("☹ OOPS!!! The task number of delete cannot be empty.");
        }
        this.taskNumber = Integer.parseInt(taskNumber);
    }

    /**
     * Execute the command
     *
     * @param tasks    the TaskList
     * @param ui       the Ui
     * @param storage  the data source
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.remove(taskNumber - 1);
        storage.update(taskNumber, task, "d");
        ui.formatPrint("Noted. I've removed this task:", "  " + task.toString(), tasks.toString());
    }

    /**
     * Check if the command is an ExitCommand
     *
     * @return           boolean stating if command is ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Return the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[" + INSTRUCTION + "] - " + taskNumber;
    }
}
