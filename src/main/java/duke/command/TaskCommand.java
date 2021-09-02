package duke.command;

import duke.main.TaskList;
import duke.task.Task;

public class TaskCommand extends Command {

    private final String description;
    private final TaskList taskList;
    private final Task.Type type;

    /**
     * Constructor for the Task Command class, to add tasks to list
     * @param description description of the task to be added
     * @param taskList task list to be modified
     * @param type type of task being added
     */
    public TaskCommand(String description, TaskList taskList, Task.Type type) {
        this.description = description;
        this.taskList = taskList;
        this.type = type;
    }

    @Override
    public String reply() {
        return "Nice! I've added the following task to your list:\n"
                + taskList.addTask(description, type)
                + "\nNow you have " + taskList.size() + " tasks in your list";
    }
}
