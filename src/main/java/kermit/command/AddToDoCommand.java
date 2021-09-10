package kermit.command;

import kermit.KermitException;
import kermit.tasks.ToDo;

public class AddToDoCommand extends AddTaskCommand {
    /**
     * AddToDo command constructor.
     * Adds todo task to a list when executed
     *
     * @param description Description of task.
     * @throws KermitException if unable to parse date (if required).
     */
    public AddToDoCommand(String description) throws KermitException {
        super(new ToDo(description), description);
    }

    /**
     * Return syntax for command.
     *
     * @return Syntax for how command is used.
     */
    protected static String getSyntax() {
        return "todo <description>";
    }
}
