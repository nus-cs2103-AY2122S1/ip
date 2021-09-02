package duke.parser;

import duke.exception.DukeException;
import duke.task.ToDo;

/**
 * Parses to-dos.
 */
public class ToDoParser {
    /**
     * Parses a <code>ToDo</code> from its text representation.
     *
     * @param text The text representation found.
     * @return The corresponding <code>ToDo</code> object.
     * @throws DukeException If the text representation cannot be parsed accurately.
     */
    protected static ToDo parse(String text) throws DukeException {
        String[] toDoDetails = text.split(" \\| ", 3);
        if (toDoDetails.length < 3) {
            throw new DukeException(String.format("Cannot parse ToDo from \n\t`%s`", text));
        }
        boolean isDone = toDoDetails[1].equals("X");
        String name = toDoDetails[2];
        return new ToDo(name, isDone);
    }
}
