package duke.parser;

import java.util.regex.Pattern;

import duke.exception.DukeException;
import duke.exception.TaskParseException;
import duke.task.Deadline;
import duke.task.Task;
import duke.util.DukeDateTime;

/**
 * Parses deadlines.
 */
public class DeadlineParser {
    private static final int NUMBER_OF_FIELDS = 4;

    /**
     * Parses a <code>Deadline</code> from its text representation.
     *
     * @param text Text representation found.
     * @return Corresponding <code>Deadline</code> object.
     * @throws DukeException If the text representation cannot be parsed accurately.
     */
    protected static Deadline parse(String text) throws DukeException {
        String[] deadlineDetails = text.split(Pattern.quote(Task.FIELD_SEPARATOR), NUMBER_OF_FIELDS);
        if (deadlineDetails.length < NUMBER_OF_FIELDS) {
            throw new TaskParseException(text);
        }
        boolean isDone = deadlineDetails[1].equals(Task.TASK_COMPLETED_SYMBOL);
        String name = deadlineDetails[2];
        DukeDateTime dueDate = DukeDateTime.parseIso(deadlineDetails[3]);
        return new Deadline(name, isDone, dueDate);
    }
}
