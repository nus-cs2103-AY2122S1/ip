package duke.commands;

import duke.exceptions.DuplicateTaskException;
import duke.tasks.ToDo;
import duke.utils.TaskList;

/**
 * Encapsulates a command to add a todo task. Executing it will return
 * the specified todo instance.
 */
public class AddTodo extends Command {
    private String taskName;

    /**
     * Creates a command that will create a todo task with the
     * specified name.
     *
     * @param taskName The name of the todo task.
     */
    public AddTodo(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public TaskList execute(TaskList taskList) throws DuplicateTaskException {
        ToDo toDo = new ToDo(this.taskName);
        taskList.add(toDo);
        return taskList;
    }
}
