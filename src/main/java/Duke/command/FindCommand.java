package Duke.command;

import Duke.Storage;
import Duke.TaskList;
import Duke.task.Todo;

public class FindCommand extends Command {
    private String task;

    public FindCommand(String input) {
        this.task = input.split(" ")[1];
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.findTasks(this.task);
    }
}
