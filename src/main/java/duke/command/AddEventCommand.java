package duke.command;

import duke.exception.IncompleteEventException;
import duke.task.Event;

/**
 * Represents the add event command of Duke.
 */
public class AddEventCommand extends AddCommand {

    /**
     * Represents Delimiter to split the user input.
     */
    private static final String DELIMITER = "/at";

    /**
     * Constructs a AddEventCommand object to add given Event Object.
     *
     * @param toAdd Event Task to add to taskList.
     */
    public AddEventCommand(Event toAdd) {
        super(toAdd);
    }

    /**
     * Generates a AddEventCommand from the userInput.
     *
     * @param userInput User Input which is used to generate the AddEventCommand.
     * @return AddEventCommand to be executed.
     * @throws IncompleteEventException if insufficient values are passed in.
     */
    public static AddEventCommand generateCommand(String userInput) throws IncompleteEventException {
        String[] str = splitEventInput(userInput);

        assert str.length == 2;

        Event add = new Event(str[0], str[1]);
        return new AddEventCommand(add);
    }

    /**
     * Splits string which contains a description and a deadline for Event Objects.
     *
     * @param userInput Input which contains a description and a deadline.
     * @return String[] containing the description at index 0 and the deadline at index 1.
     * @throws IncompleteEventException If no deadline, description, or insufficient fields given.
     */
    private static String[] splitEventInput(String userInput) throws IncompleteEventException {
        String[] str = userInput.split(DELIMITER);

        if (str.length == 1) {
            throw new IncompleteEventException();
        } else {
            String[] first = str[0].split(SPACE);
            String[] second = str[1].split(SPACE);

            String description = getDescription(first);
            String deadline = getDeadline(second);

            return new String[]{description, deadline};
        }
    }

}
