package Duke.Command;

import Duke.Main.TaskList;
import Duke.Task.Task;

public class TaskCommand extends Command {

    private String description;
    private TaskList taskList;
    private Task.Type type;
    public TaskCommand(String description, TaskList taskList, Task.Type type) {
        super(description, taskList);
        this.description = description;
        this.taskList = taskList;
        this.type = type;
    }

    @Override
    public String reply() {
        return "Nice! I've added the following task to your list:\n" +
                taskList.addTask(description, type) +
                "\nNow you have " + taskList.size() + " tasks in your list";
    }
}
