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

    public TodoCommand(String description, TaskList taskList) {
        this.taskList = taskList;
        this.description = description;
    }

    @Override
    public String execute() throws DukeException {
        Todo todo = new Todo(description);
        return taskList.addItem(todo);
    }
}
