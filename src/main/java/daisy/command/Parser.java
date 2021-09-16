package daisy.command;

import java.time.LocalDate;

import daisy.DaisyException;

/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Returns the command to be executed based on the user's command message.
     *
     * @param commandMessage Command message from user's input.
     * @return Command to be executed.
     * @throws DaisyException  If correct command cannot be created.
     */
    public static Command parse(String commandMessage) throws DaisyException {
        assert commandMessage != null : "Command message is null";
        String[] messages = commandMessage.split(" ", 2);
        String command = messages[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(getTaskNumber(messages[1]));
        case "todo":
            return new AddCommand(AddCommand.TaskType.TODO,
                    new String[] {checkDescriptionAvailable(messages[1], command)});
        case "deadline":
            String deadlineDescription = checkDescriptionAvailable(messages[1], command);
            String[] deadlineParameters = getParameters(deadlineDescription, command, " /by ");
            return new AddCommand(AddCommand.TaskType.DEADLINE, deadlineParameters);
        case "event":
            String eventDescription = checkDescriptionAvailable(messages[1], command);
            String[] eventParameters = getParameters(eventDescription, command, " /at ");
            return new AddCommand(AddCommand.TaskType.EVENT, eventParameters);
        case "delete":
            return new DeleteCommand(getTaskNumber(messages[1]));
        case "on":
            String dateString = checkDescriptionAvailable(messages[1], command);
            LocalDate date = LocalDate.parse(dateString.trim());
            return new OnCommand(date);
        case "find":
            return new FindCommand(checkDescriptionAvailable(messages[1], command));
        case "tag":
            String tagDescription = checkDescriptionAvailable(messages[1], command);
            String[] tagParameters = tagDescription.split(" ", 2);
            return new TagCommand(getTaskNumber(tagParameters[0]), tagParameters[1].split("/"));
        default:
            throw new DaisyException("I'm sorry, but I don't know what that means :-(\n");
        }
    }

    /**
     * Checks that the description is available.
     *
     * @param des User's command message excluding the command word.
     * @param command Type of command.
     * @return Description string.
     * @throws DaisyException If description is empty.
     */
    private static String checkDescriptionAvailable(String des, String command) throws DaisyException {
        assert des != null : "Description to check is null";
        String description = des.trim();
        if (description.isEmpty()) {
            throw new DaisyException(String.format("The description of a %s cannot be empty!\n", command));
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
     * @throws DaisyException If description does not contain a date.
     */
    private static String[] getParameters(String des, String command, String regex) throws DaisyException {
        assert des != null : "Description to check is null";
        if (!des.contains(regex)) {
            throw new DaisyException(String.format("The new %s is missing a date!\n", command));
        } else {
            return des.split(regex);
        }
    }

    /**
     * Retrieves the task number from the user's command message.
     *
     * @param des User's command message excluding the command word.
     * @return Task number of task to be edited.
     * @throws DaisyException If no task number is provided.
     */
    private static int getTaskNumber(String des) throws DaisyException {
        assert des != null : "Description to check is null";
        String description = des.trim();
        if (description.isEmpty()) {
            throw new DaisyException("I do not know which task to change!\n");
        } else {
            return Integer.parseInt(description);
        }
    }
}
