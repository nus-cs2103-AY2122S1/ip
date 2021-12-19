package kermit.command;

import kermit.KermitException;
import kermit.tasks.Deadline;

/**
 * AddDeadlineCommand is used to create new deadline tasks.
 */
public class AddDeadlineCommand extends AddDateDependentTaskCommand {

    /**
     * Constructs AddDeadlineCommand.
     *
     * @param description description of deadline.
     * @param dateString date of the deadline in the form dd-mm-yyyy.
     * @throws KermitException if deadline task is not valid.
     */
    public AddDeadlineCommand(String description, String dateString) throws KermitException {
        super(new Deadline(description, AddDateDependentTaskCommand.parseDate(dateString)), description);
    }

    /**
     * Returns syntax for command.
     *
     * @return Syntax for how command is used.
     */
    protected static String getSyntax() {
        return "deadline <description> /by <dd-mm-yyyy>";
    }
}
