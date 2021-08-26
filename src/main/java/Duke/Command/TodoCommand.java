package Duke.Command;

import Duke.Main.TaskList;
import Duke.Task.Task;

public class TodoCommand extends Command {

    private String description;
    private TaskList taskList;
    public TodoCommand(String description, TaskList taskList) {
        super(description, taskList);
        this.description = description;
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        return "Nice! I've added the following task to your list:\n" +
                taskList.addTask(description, Task.Type.TODO) +
                "\nNow you have " + taskList.size() + " tasks in your list";
    }
}
