package duke.util;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Encapsulates the find command class.
 */
public class FindCommand implements Command {
    private String[] userInput;

    /**
     * Constructor for FindCommand.
     *
     * @param userInput Array of user input for find command.
     */
    public FindCommand(String[] userInput) {
        this.userInput = userInput;
    }

    /**
     * Returns string response when user enters a find command.
     *
     * @param tasks List of tasks.
     * @param ui Ui that prints message to users.
     * @param storage Storage to save and load data.
     * @return String representation of response for find command.
     * @throws DukeException If no keyword for find command entered.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (userInput.length == 1) {
            throw new DukeException("Please enter a keyword to find matching tasks...");
        }

        String keyword = userInput[1].trim();
        return tasks.findMatchingTasks(keyword);
    }
}
