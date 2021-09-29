package Duke.command;

import Duke.TaskList;

public class DeleteCommand extends Command {

    private int task;

    public DeleteCommand(String input) {
        this.task = Integer.parseInt(input.split(" ")[1]);
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.deleteTask(this.task);
    }
}
