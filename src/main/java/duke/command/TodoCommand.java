package duke.command;

import duke.util.Keyword;
import duke.util.Message;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command for new todo.
 *
 * @author marcuspeh
 * @version A-Assertions
 * @since 6 Sep 2021
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
     * @return message to be used by either the graphic UI or command line UI.
     */
    @Override
    public Message execute(TaskList taskList, Ui ui) {
        assert taskList != null : " Tasklist is required by command.";
        assert ui != null : " Ui is required by command.";

        try {
            assert message.length() > Keyword.TODOS.length() : "Find is in the following format 'find <parameter>'";
            return taskList.addTodo(message.substring(Keyword.TODOS.length() + 1));
        } catch (IndexOutOfBoundsException e) {
            return ui.formatTodoErrorMessage();
        }
    }
}
