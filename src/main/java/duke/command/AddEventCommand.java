package duke.command;

import java.time.format.DateTimeParseException;
import duke.parser.Parser;
import duke.task.Event;
import duke.task.Task;

/**
 * Adds an event task to the list of user's tasks.
 */
public class AddEventCommand extends AddCommand {
    public static final String COMMAND_IDENTIFIER = "event";

    private static final String DATE_PARAM_IDENTIFIER = "at";

    /**
     * Returns the AddEvent command represented by the user input.
     *
     * @param userInput String input provided by the user.
     * @return AddEvent user command.
     * @throws MalformedCommandException If userInput is incorrectly formatted for an AddEvent command.
     */
    public static Command create(String userInput) throws MalformedCommandException {
        try {
            String commandParams = Parser.getCommandParams(userInput);
            String taskDescription = getTaskDescription(commandParams);
            String eventDate = getEventDate(commandParams);
            Task task = new Event(taskDescription, eventDate);
            return new AddEventCommand(task);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new MalformedCommandException("Creating an event needs to follow the following "
                    + "format: event [description] /at [d/MM/yyyy HHmm]");
        }
    }

    private AddEventCommand(Task task) {
        this.task = task;
    }

    private static String getEventDate(String commandParams) throws ArrayIndexOutOfBoundsException {
        String[] commandParamsSplit = commandParams.split(DATE_DELIMITER, 2);
        String eventDate = "";
        if (commandParamsSplit[1].startsWith(DATE_PARAM_IDENTIFIER)) {
            eventDate = commandParamsSplit[1].replaceFirst(DATE_PARAM_IDENTIFIER, "").stripLeading();
        }
        return eventDate;
    }
}
