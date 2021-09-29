package Duke.command;

import Duke.TaskList;
import Duke.task.Todo;

public class TodoCommand extends Command {
    private Todo todo;

    public TodoCommand(String input) {
        String task = input.split(" ")[1];
        this.todo = new Todo(task);
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.addTask(this.todo);
    }
}
