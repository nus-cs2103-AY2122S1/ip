package duke.command;

import java.time.format.DateTimeParseException;
import duke.task.Event;
import duke.task.Task;

/**
 * Adds an event task to the list of user's tasks.
 */
public class AddEventCommand extends AddCommand {
    public static final String COMMAND_IDENTIFIER = "event";

    /**
     * Returns the AddEvent command represented by the user input.
     *
     * @param userInput String input provided by the user.
     * @return AddEvent user command.
     * @throws MalformedCommandException If userInput is incorrectly formatted for an AddEvent command.
     */
    public static Command create(String userInput) throws MalformedCommandException {
        assert userInput != null : "User input should be null for the creation of a Command";

        try {
            String userParams = userInput.split(" ", 2)[1];
            String[] userParamsSplit = userParams.split(" /", 2);
            String description = userParamsSplit[0];
            String at = "";
            if (userParamsSplit[1].startsWith("at")) {
                at = userParamsSplit[1].replaceFirst("at", "").stripLeading();
            }
            Task task = new Event(description, at);
            return new AddEventCommand(task);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new MalformedCommandException("Creating an event needs to follow the following format: " +
                "event [description] /at [d/MM/yyyy HHmm]");
        }
    }

    private AddEventCommand(Task task) {
        this.task = task;
    }
}
