package duke.commands;

import duke.Task;
import duke.TaskList;
import duke.ToDo;

/**
 * This class handles commands meant for adding tasks with just description.
 */
public class AddTodoCommand implements Command {

    private String description;

    /**
     * Constructor which takes in task description.
     *
     * @param description String that is the name of task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList) {
        Task toDo = new ToDo(description);
        String output = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
            taskList.addToList(toDo), taskList.taskCount());
        return output;
    }
}
