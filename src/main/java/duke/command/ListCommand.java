package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;

/**
 * This class abstracts the list command that the user wants to execute.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Execute the command to show the list to the user.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String str = "Here are the tasks in your list:";
        StringBuilder stringBuilder = new StringBuilder(str);
        int index = 1;
        for (Task task : tasks.getAllTasks()) {
            stringBuilder.append("\n").append(index++).append(".").append(task);
        }
        return stringBuilder.toString();
    }
}
