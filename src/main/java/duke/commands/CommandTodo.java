package duke.commands;

import duke.TaskArrayList;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Command to execute when user types "todo"
 */
public class CommandTodo extends Command {
    public static final String HELP_COMMAND = "todo";
    public static final String HELP_DESCRIPTION = "Add a new todo task";
    public static final String HELP_USAGE = "Usage: todo task_name\n"
            + "Add a new todo task\n"
            + "\ttask_name\tname of the task to add";

    public CommandTodo(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }

    @Override
    public String run() throws DukeException {
        boolean hasWrongArgumentCount = (cmdArgsArr.length != 2);

        if (hasWrongArgumentCount) {
            throw new DukeException(Todo.USAGE_TEXT);
        }

        Task toAdd = new Todo(cmdArgsArr[1]);
        return taskList.addTask(toAdd);
    }
}
