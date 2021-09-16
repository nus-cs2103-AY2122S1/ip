package duke.util;

import duke.exception.DukeException;
import duke.exception.InvalidTaskDoneException;

import duke.task.TaskList;

/**
 * Encapsulates the done command class.
 */
public class DoneCommand implements Command{
    private String[] userInput;

    /**
     * Constructor for DoneCommand.
     *
     * @param userInput Array of user input for done command.
     */
    public DoneCommand(String[] userInput) {
        this.userInput = userInput;
    }

    /**
     * Returns string response when user enters a done command.
     *
     * @param tasks List of tasks.
     * @param ui Ui that prints message to users.
     * @param storage Storage to save and load data.
     * @return String representation of duke's response for done command.
     * @throws DukeException If no task number or invalid task number entered.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (userInput.length == 1) {
            throw new DukeException("No task number entered!");
        }

        assert userInput.length == 2;

        int doneNumber = Integer.parseInt(userInput[1].trim()) - 1;
        boolean isBelowValidIndex = doneNumber < 0;
        boolean isAboveValidIndex = doneNumber > tasks.getListLength()-1;

        if (isBelowValidIndex || isAboveValidIndex) {
            throw new InvalidTaskDoneException();
        } else {
            return tasks.setTaskAsDone(doneNumber);
        }
    }
}
