package kermit.command;

import kermit.KermitException;
import kermit.tasks.Event;

/**
 * AddEventCommand is used to create new event tasks.
 */
public class AddEventCommand extends AddDateDependentTaskCommand {

    /**
     * Constructs AddEventCommand.
     *
     * @param description description of the event.
     * @param dateString date of the event in the form dd-mm-yyyy.
     * @throws KermitException if the event is not valid.
     */
    public AddEventCommand(String description, String dateString) throws KermitException {
        super(new Event(description, AddDateDependentTaskCommand.parseDate(dateString)), description);
    }

    /**
     * Returns syntax for command.
     *
     * @return Syntax for how command is used.
     */
    protected static String getSyntax() {
        return "event <description> /at <dd-mm-yyyy>";
    }
}
