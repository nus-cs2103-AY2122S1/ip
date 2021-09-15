package duke.util;

import duke.exception.DukeException;
import duke.exception.InvalidTaskDeletionException;
import duke.task.TaskList;

/**
 * Encapsulates a delete command class.
 */
public class DeleteCommand implements Command {
    private String[] userInput;

    /**
     * Constructor for DeleteCommand.
     *
     * @param userInput Array of user input for delete command.
     */
    public DeleteCommand(String[] userInput) {
        this.userInput = userInput;
    }

    /**
     * Returns string response when user enters a delete command.
     *
     * @param tasks List of tasks.
     * @param ui Ui that prints message to users.
     * @param storage Storage to save and load data.
     * @return String representation of duke's response for delete command.
     * @throws DukeException If no task number entered or invalid task number entered.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (userInput.length == 1) {
            throw new DukeException("No task number entered!");
        }

        assert userInput.length == 2;

        int deleteNumber = Integer.parseInt(userInput[1].trim()) - 1;
        boolean isBelowValidIndex = deleteNumber < 0;
        boolean isAboveValidIndex = deleteNumber > tasks.getListLength()-1;

        if (isBelowValidIndex || isAboveValidIndex) {
            throw new InvalidTaskDeletionException();
        } else {
            return tasks.deleteFromList(deleteNumber);
        }
    }
}
