package duke.command;

import duke.ArchiveList;
import duke.TaskList;
import duke.exception.IncompleteEventException;
import duke.task.Event;
import duke.task.Task;

/**
 * Representation for the add event command of Duke.
 */
public class AddEventCommand extends AddCommand {

    private static final String DELIMITER = "/at";

    /**
     * Constructor for AddCommand.
     *
     * @param toAdd Task to add to taskList.
     */
    public AddEventCommand(Task toAdd) {
        super(toAdd);
    }

    /**
     * Factory method which generates the AddEventCommand from the userInput.
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
