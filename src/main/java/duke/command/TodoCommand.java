package duke.command;

import duke.util.Keyword;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command for new todo.
 *
 * @author marcuspeh
 * @version A-JavaDoc
 * @since 23 Aug 2021
 */
public class TodoCommand implements Command {
    /** Stores the message entered by the user. */
    private String message;

    /** Constructor for duke.task.Deadlines.TodoCommand.
     *
     * @param message Stores the message entered by the user.
     */
    TodoCommand(String message) {
        this.message = message;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param taskList duke.main.TaskList to execute the command.
     * @param ui To interact with the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTodo(message.substring(Keyword.TODOS.length() + 1));
    }
}
