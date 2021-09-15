package duke.util;

import duke.exception.DukeException;
import duke.exception.NoDescriptionAndTimeException;

import duke.task.Deadline;
import duke.task.TaskList;

import java.time.LocalDateTime;

/**
 * Encapsulates the deadline command class.
 */
public class DeadlineCommand implements Command{
    private String[] userInput;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param userInput Array of user input for deadline command.
     */
    public DeadlineCommand(String[] userInput) {
        this.userInput = userInput;
    }

    /**
     * Returns string response when user enters a deadline command.
     *
     * @param tasks List of tasks.
     * @param ui Ui that prints message to users.
     * @param storage Storage to save and load data.
     * @return String representation of duke's response for deadline command.
     * @throws DukeException If there is no description or time input for deadline.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (userInput.length == 1) {
            throw new NoDescriptionAndTimeException("deadline");
        }

        String[] deadlineDetail = Parser.parseDescriptionAndTime(userInput, "/by", "deadline");

        assert deadlineDetail.length == 2 : "deadlineDetail should be length of 2";

        LocalDateTime localDateTime = Parser.parseDateTime(deadlineDetail[1].trim());
        String description = deadlineDetail[0].trim();
        Deadline deadline = new Deadline(description, localDateTime);

        return tasks.addTaskToList(deadline);
    }


}
