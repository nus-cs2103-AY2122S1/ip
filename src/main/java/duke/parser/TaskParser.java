package duke.parser;

import duke.exception.DukeException;
import duke.exception.TaskParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Parses tasks.
 */
public class TaskParser {
    /**
     * Parses a <code>Task</code> from its text representation.
     *
     * @param text Text representation found.
     * @return Corresponding <code>Task</code> object.
     * @throws DukeException If the text does not start with a character corresponding to a valid <code>Task</code>.
     */
    public static Task parse(String text) throws DukeException {
        char taskType = text.charAt(0);
        switch (taskType) {
        case ToDo.TEXT_ENCODING_CHAR:
            return ToDoParser.parse(text);
        case Deadline.TEXT_ENCODING_CHAR:
            return DeadlineParser.parse(text);
        case Event.TEXT_ENCODING_CHAR:
            return EventParser.parse(text);
        default:
            throw new TaskParseException(text);
        }
    }
}
