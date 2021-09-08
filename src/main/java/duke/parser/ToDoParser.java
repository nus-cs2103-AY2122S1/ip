package duke.parser;

import java.util.regex.Pattern;

import duke.exception.DukeException;
import duke.exception.TaskParseException;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Parses to-dos.
 */
public class ToDoParser {
    private static final int NUMBER_OF_FIELDS = 3;

    /**
     * Parses a <code>ToDo</code> from its text representation.
     *
     * @param text Text representation found.
     * @return Corresponding <code>ToDo</code> object.
     * @throws DukeException If the text representation cannot be parsed accurately.
     */
    protected static ToDo parse(String text) throws DukeException {
        String[] toDoDetails = text.split(Pattern.quote(Task.FIELD_SEPARATOR), NUMBER_OF_FIELDS);
        if (toDoDetails.length < NUMBER_OF_FIELDS) {
            throw new TaskParseException(text);
        }
        boolean isDone = toDoDetails[1].equals(Task.TASK_COMPLETED_SYMBOL);
        String name = toDoDetails[2];
        return new ToDo(name, isDone);
    }
}
