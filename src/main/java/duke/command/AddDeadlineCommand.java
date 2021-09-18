package duke.command;

import java.time.format.DateTimeParseException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Adds a deadline task to the list of user's tasks.
 */
public class AddDeadlineCommand extends AddCommand {
    public static final String COMMAND_IDENTIFIER = "deadline";

    private static final String DATE_PARAM_IDENTIFIER = "by";

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
            String commandParams = Parser.getCommandParams(userInput);
            String taskDescription = getTaskDescription(commandParams);
            String dueDate = getDueDate(commandParams);
            Task task = new Deadline(taskDescription, dueDate);
            return new AddDeadlineCommand(task);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new MalformedCommandException("Creating an deadline needs to follow the following"
                    + " format: deadline [description] /by [d/MM/yyyy HHmm]");
        }
    }

    private AddDeadlineCommand(Task task) {
        this.task = task;
    }

    private static String getDueDate(String commandParams) throws DateTimeParseException {
        String[] commandParamsSplit = commandParams.split(DATE_DELIMITER, 2);
        String dueDate = "";
        if (commandParamsSplit[1].startsWith(DATE_PARAM_IDENTIFIER)) {
            dueDate = commandParamsSplit[1].replaceFirst(DATE_PARAM_IDENTIFIER, "").stripLeading();
        }
        return dueDate;
    }
}
