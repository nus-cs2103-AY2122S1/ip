package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Class to encapsulate a UpdateCommand
 */
public class UpdateCommand extends Command {
    public static final String INSTRUCTION_UPDATE = "update";
    protected int taskNumber;
    protected String flag;
    protected String modification;

    /**
     * Class constructor for UpdateCommand Class specifying the task number
     */
    public UpdateCommand(String taskNumber, String flag, String modification) throws DukeException {
        if (taskNumber.equals("")) {
            throw new DukeException("OOPS!!! The task number of update cannot be empty.");
        }
        if (flag.equals("")) {
            throw new DukeException("OOPS!!! The flag of update cannot be empty.");
        }
        if ((flag.equals("-t") || flag.equals("-d")) && modification.equals("")) {
            throw new DukeException("OOPS!!! The modification of update cannot be empty.");
        }

        this.taskNumber = Integer.parseInt(taskNumber);
        this.flag = flag;
        this.modification = modification;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        switch (flag) {
        case "-t":
            task = tasks.changeDate(taskNumber - 1, modification);
            break;
        case "-d":
            task = tasks.changeDescription(taskNumber - 1, modification);
            break;
        case "-D":
            task = tasks.markAsIncomplete(taskNumber - 1);
            break;
        default:
            throw new DukeException("Invalid flag");
        }
        storage.update(taskNumber, task, "m");
        return "Nice! I've modified this task:\n" + task.toString();
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
        return "[" + INSTRUCTION_UPDATE + "] - " + taskNumber;
    }
}
