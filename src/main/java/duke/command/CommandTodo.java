package duke.command;

import duke.exception.DukeArgumentException;
import duke.exception.DukeCommandException;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents a command to add a ToDo task.
 */
public class CommandTodo extends DukeCommand {
    private ToDo task;

    /**
     * Creates a new CommandTodo command.
     *
     * @param t Task that is to be added.
     */
    public CommandTodo(ToDo t) {
        this.task = t;
    }

    /**
     * Adds the task to the task list.
     *
     * @param tl Task list for the user.
     */
    @Override
    public String execute(TaskList tl) {
        return tl.addTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    };

    /**
     * Parses the user input into the right format for the command
     *
     * @param userArgs Arguments to the command as provided by the user.
     */
    public static DukeCommand parseCommand(String[] userArgs) throws DukeCommandException, DukeArgumentException {
        assert userArgs != null;
        assert userArgs.length != 0;
        assert userArgs[0].equals("todo");
        if (userArgs.length < 2) {
            throw new DukeCommandException("todo");
        }

        assert userArgs.length == 2;

        if (userArgs[1].equals("")) {
            throw new DukeArgumentException("Todo name cannot be empty");
        } else if (userArgs[1].contains("|")) {
            throw new DukeArgumentException("Todo name cannot contain \"|\".");
        }

        ToDo task = new ToDo(userArgs[1]);
        return new CommandTodo(task);
    }
}
