package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Todo;

/**
 * The command to add a TODO typed task.
 */
public class TodoCommand extends Command {

    private String description;
    private TaskList taskList;

    /**
     * Constructor for TodoCommand.
     * @param description The description of the task.
     * @param taskList The list of tasks.
     */
    public TodoCommand(String description, TaskList taskList) {
        this.taskList = taskList;
        this.description = description;
    }

    /**
     * Returns an output message after executing the todo command.
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        Todo todo = new Todo(description);
        return taskList.addItem(todo);
    }
}
