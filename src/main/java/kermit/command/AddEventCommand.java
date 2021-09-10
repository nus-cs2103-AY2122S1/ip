package kermit.command;

import kermit.KermitException;
import kermit.tasks.Event;

public class AddEventCommand extends AddDateDependentTaskCommand {

    public AddEventCommand(String description, String dateString) throws KermitException {
        super(new Event(description, AddDateDependentTaskCommand.parseDate(dateString)), description);
    }

    /**
     * Return syntax for command.
     *
     * @return Syntax for how command is used.
     */
    protected static String getSyntax() {
        return "event <description> /at <dd-mm-yyyy>";
    }
}
