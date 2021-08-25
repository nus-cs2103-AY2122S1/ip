package duke.command;

import duke.data.Storage;
import duke.data.TaskList;

/**
 * Represents a processor that can retrieve the task list. A subclass of the Processor class.
 */
public class GetListCommand extends Command {
    /**
     * Constructor of the class `GetListProcessor`.
     */
    public GetListCommand() {
        super("list");
        this.message = "Here are the tasks in your list:\n";
    }

    /**
     * Generates the message to be printed.
     *
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
