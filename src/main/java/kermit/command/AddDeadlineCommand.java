package kermit.command;

import kermit.KermitException;
import kermit.tasks.Deadline;
public class AddDeadlineCommand extends AddDateDependentTaskCommand {

    public AddDeadlineCommand(String description, String dateString) throws KermitException {
        super(new Deadline(description, AddDateDependentTaskCommand.parseDate(dateString)), description);
    }

    /**
     * Return syntax for command.
     *
     * @return Syntax for how command is used.
     */
    protected static String getSyntax() {
        return "deadline <description> /by <dd-mm-yyyy>";
    }
}
