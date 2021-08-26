package Duke.Command;

import Duke.Main.TaskList;
import Duke.Task.Task;

public class DeadlineCommand extends Command {

    private TaskList taskList;
    private String description;
    public DeadlineCommand(String description, TaskList taskList) {
        super(description, taskList);
        this.description = description;
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        return "Nice! I've added the following task to your list:\n" +
                taskList.addTask(description, Task.Type.DEADLINE) +
                "\nNow you have " + taskList.size() + " tasks in your list";
    }
}
