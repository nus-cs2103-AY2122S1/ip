package duke.command;

import duke.data.Storage;
import duke.data.TaskList;

/**
 * Represents a command that retrieves the task list. A subclass of the Command class.
 */
public class GetListCommand extends Command {
    /**
     * Constructor of the class `GetListCommand`.
     */
    public GetListCommand() {
        super("list");
        this.message = "Here are the tasks in your list:\n";
    }

    /**
     * Executes the command. Updates the message to be printed.
     * Returns true because the program is still running.
     *
     * @param taskList A list of tasks.
     * @param storage An instance of Storage that can read from and write to the hard disk.
     * @return Whether the program is still running.
     */
    @Override
    public boolean execute(TaskList taskList, Storage storage) {
        int len = taskList.getNumOfTasks();
        for (int i = 0; i < len; i++) {
            this.message += String.format("%o.%s\n", i + 1, taskList.get(i).toString());
        }
        return true;
    }
}
