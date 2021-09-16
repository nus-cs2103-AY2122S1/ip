package duke.commands;

import duke.TaskArrayList;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Command to execute when user types "todo".
 */
public class CommandTodo extends Command {
    public static final String HELP_COMMAND = "todo";
    public static final String HELP_DESCRIPTION = "Add a new todo task";
    public static final String HELP_USAGE =
            "Usage: todo task_name\n"
            + HELP_DESCRIPTION + "\n"
            + "\ttask_name\tname of the task to add";

    private static final int REQUIRED_ARG_COUNT = 2;

    public CommandTodo(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }

    @Override
    public String run() throws DukeException {
        if (hasWrongArgumentCount(REQUIRED_ARG_COUNT)) {
            throw new DukeException(HELP_USAGE);
        }

        Task toAdd = new Todo(cmdArgsArr[1]);
        return taskList.addTask(toAdd);
    }
}
