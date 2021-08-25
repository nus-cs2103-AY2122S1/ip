package duke.command;

import duke.task.Task;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class DoneCommand extends Command {
    protected int taskNumber;
    public static final String INSTRUCTION_DONE = "done";

    /**
     * Class constructor for DoneCommand Class specifying the task number
     */
    public DoneCommand(String taskNumber) throws DukeException {
        if (taskNumber.equals("")) {
            throw new DukeException("☹ OOPS!!! The task number of done cannot be empty.");
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
        Task task = tasks.markAsDone(taskNumber - 1);
        storage.update(taskNumber, task, "m");
        ui.formatPrint("Nice! I've marked this task as done:", "  [X] " + task.toString());
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
        return "[" + INSTRUCTION_DONE + "] - " + taskNumber;
    }
}
