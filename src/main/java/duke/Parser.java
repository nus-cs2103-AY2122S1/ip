package duke;

import java.time.LocalDate;

import duke.command.*;

/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Returns the command to be executed based on the user's command message.
     *
     * @param commandMessage Command message from user's input.
     * @return Command to be executed.
     * @throws DukeException  If correct command cannot be created.
     */
    public static Command parse(String commandMessage) throws DukeException {
        String[] message = commandMessage.split(" ", 2);
        String command = message[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done": {
            int taskNumber = getTaskNumber(message[1]);
            return new DoneCommand(taskNumber);
        }
        case "todo":
            return new AddCommand(AddCommand.TaskType.TODO,
                    new String[] {checkDescriptionAvailable(message[1], command)});
        case "deadline": {
            String description = checkDescriptionAvailable(message[1], command);
            String[] parameters = getParameters(description, command, " /by ");
            return new AddCommand(AddCommand.TaskType.DEADLINE, parameters);
        }
        case "event": {
            String description = checkDescriptionAvailable(message[1], command);
            String[] parameters = getParameters(description, command, " /at ");
            return new AddCommand(AddCommand.TaskType.EVENT, parameters);
        }
        case "delete": {
            int taskNumber = getTaskNumber(message[1]);
            return new DeleteCommand(taskNumber);
        }
        case "on": {
            String dateString = checkDescriptionAvailable(message[1], command);
            LocalDate date = LocalDate.parse(dateString.trim());
            return new OnCommand(date);
        }
        case "find":
            String query = checkDescriptionAvailable(message[1], command);
            return new FindCommand(query);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
        }
    }

    /**
     * Checks that the description is available.
     *
     * @param des User's command message excluding the command word.
     * @param command Type of command.
     * @return Description string.
     * @throws DukeException If description is empty.
     */
    private static String checkDescriptionAvailable(String des, String command) throws DukeException {
        String description = des.trim();
        if (description.isEmpty()) {
            throw new DukeException(String.format("The description of a %s cannot be empty!\n", command));
        } else {
            return des;
        }
    }

    /**
     * Checks that the description contains parameters required for command.
     *
     * @param des User's command message excluding the command word.
     * @param command Type of command.
     * @param regex Regex string to split description.
     * @return String array of parameters.
     * @throws DukeException If description does not contain a date.
     */
    private static String[] getParameters(String des, String command, String regex) throws DukeException {
        if (!des.contains(regex)) {
            throw new DukeException(String.format("The new %s is missing a date!\n", command));
        } else {
            return des.split(regex);
        }
    }

    /**
     * Retrieves the task number from the user's command message.
     *
     * @param des User's command message excluding the command word.
     * @return Task number of task to be edited.
     * @throws DukeException If no task number is provided.
     */
    private static int getTaskNumber(String des) throws DukeException {
        String description = des.trim();
        if (description.isEmpty()) {
            throw new DukeException("I do not know which task to change!\n");
        } else {
            return Integer.parseInt(description);
        }
    }
}
