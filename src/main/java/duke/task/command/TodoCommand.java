package duke.task.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Todo;

/**
 * Class to execute the todo command.
 */
public class TodoCommand extends Command {

    private TaskList taskList;
    private String description;

    /**
     * Instantiates a new command object
     * @param taskList list in which command is to be added
     * @param description description of the todo task
     */
    public TodoCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.description = description;
    }

    /**
     * Executes the command.
     *
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        Todo todo = new Todo(description);
        return taskList.addItem(todo);
    }
}
