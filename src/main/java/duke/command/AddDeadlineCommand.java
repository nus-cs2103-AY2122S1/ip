package duke.command;

import java.time.format.DateTimeParseException;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Adds a deadline task to the list of user's tasks.
 */
public class AddDeadlineCommand extends AddCommand {
    public static final String COMMAND_IDENTIFIER = "deadline";

    /**
     * Returns the AddDeadline command represented by the user input.
     *
     * @param userInput String input provided by the user.
     * @return AddDeadline user command.
     * @throws MalformedCommandException If userInput is incorrectly formatted for an AddDeadline command.
     */
    public static Command create(String userInput) throws MalformedCommandException {
        assert userInput != null : "User input should be null for the creation of a Command";

        try {
            String userParams = userInput.split(" ", 2)[1];
            String[] userParamsSplit = userParams.split(" /", 2);
            String description = userParamsSplit[0];
            String by = "";
            if (userParamsSplit[1].startsWith("by")) {
                by = userParamsSplit[1].replaceFirst("by", "").stripLeading();
            }
            Task task = new Deadline(description, by);
            return new AddDeadlineCommand(task);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new MalformedCommandException("Creating an deadline needs to follow the following format: " +
                "deadline [description] /by [d/MM/yyyy HHmm]");
        }
    }

    private AddDeadlineCommand(Task task) {
        this.task = task;
    }
}
