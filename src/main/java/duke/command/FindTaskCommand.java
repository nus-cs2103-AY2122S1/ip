package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a command that finds a task with keyword. A subclass of Command.
 */
public class FindTaskCommand extends Command {
    String keyword;

    public FindTaskCommand(String keyword) {
        super("find");
        this.keyword = keyword;
        this.message = "Here are the tasks I found:\n";
    }

    @Override
    public boolean execute(TaskList tasks, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.filter(this.keyword);
        int len = filteredTasks.size();
        for (int i = 0; i < len; i++) {
            this.message += String.format("%o.%s\n", i + 1, filteredTasks.get(i).toString());
        }
        return true;
    }
}
