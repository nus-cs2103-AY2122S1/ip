package Duke.command;

import Duke.TaskList;

public class DoneCommand extends Command {

    private int task;

    public DoneCommand(String input) {
        this.task = Integer.parseInt(input.split(" ")[1]);
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.markAsDone(this.task);
    }
}
