package duke.util;

import duke.exception.DukeException;
import duke.exception.NoDescriptionException;
import duke.task.TaskList;

/**
 * Encapsulates the todo command class.
 */
public class TodoCommand implements Command {
    private String[] userInput;

    /**
     * Constructor for TodoCommand.
     *
     * @param userInput Array of user input for todo command.
     */
    public TodoCommand(String[] userInput) {
        this.userInput = userInput;
    }

    /**
     * Returns string response when user enters a todo command.
     *
     * @param tasks List of tasks.
     * @param ui Ui that prints message to users.
     * @param storage Storage to save and load data.
     * @return String representation of duke's response for todo command.
     * @throws DukeException If no description entered for todo command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (userInput.length == 1) {
            throw new NoDescriptionException("todo");
        }

        assert userInput.length == 2;

        return tasks.addTodoToList(userInput[1].trim());
    }
}
