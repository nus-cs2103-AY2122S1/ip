package duke.command;

import duke.exception.IncompleteDeadlineException;
import duke.task.Deadline;

/**
 * Represents the add deadline command of Duke.
 */
public class AddDeadlineCommand extends AddCommand {

    private static final String DELIMITER = "/by";

    /**
     * Constructs a AddDeadlineCommand object to add given deadline object.
     *
     * @param toAdd Deadline Task to add to taskList.
     */
    public AddDeadlineCommand(Deadline toAdd) {
        super(toAdd);
    }

    /**
     * Generates a AddDeadlineCommand from the userInput.
     *
     * @param userInput User Input which is used to generate the AddDeadlineCommand.
     * @return AddDeadLineCommand to be executed.
     * @throws IncompleteDeadlineException if insufficient values are passed in.
     */
    public static AddDeadlineCommand generateCommand(String userInput) throws IncompleteDeadlineException {
        String[] str = splitDeadlineInput(userInput);

        assert str.length == 2;

        Deadline add = new Deadline(str[0], str[1]);
        return new AddDeadlineCommand(add);
    }

    /**
     * Splits string which contains a description and a deadline for Deadline Objects.
     *
     * @param userInput Input which contains a description and a deadline.
     * @return String[] containing the description at index 0 and the deadline at index 1.
     * @throws IncompleteDeadlineException If no deadline, description, or insufficient fields given.
     */
    public static String[] splitDeadlineInput(String userInput) throws IncompleteDeadlineException {
        String[] str = userInput.split(DELIMITER);

        if (str.length == 1) {
            throw new IncompleteDeadlineException();
        } else {
            String[] first = str[0].split(SPACE);
            String[] second = str[1].split(SPACE);

            String description = getDescription(first);
            String deadline = getDeadline(second);

            return new String[]{description, deadline};
        }
    }
}
