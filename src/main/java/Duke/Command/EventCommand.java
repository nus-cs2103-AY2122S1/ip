package Duke.Command;

import Duke.Main.TaskList;
import Duke.Task.Task;

public class EventCommand extends Command {

    private String description;
    private TaskList taskList;
    public EventCommand(String description, TaskList taskList) {
        super(description, taskList);
        this.description = description;
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        return "Nice! I've added the following task to your list:\n" +
                taskList.addTask(description, Task.Type.EVENT) +
                "\nNow you have " + taskList.size() + " tasks in your list";
    }
}
