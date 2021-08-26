package duke.parser;

import duke.exception.DukeException;
import duke.task.Task;

public class TaskParser {
    /**
     * Parses a <code>Task</code> from its text representation.
     * @param text the text representation found
     * @return the corresponding <code>Task</code> object
     * @throws DukeException if the text does not start with a character corresponding to a valid <code>Task</code>
     */
    public static Task parse(String text) throws DukeException {
        char taskType = text.charAt(0);
        switch (taskType) {
        case 'T':
            return ToDoParser.parse(text);
        case 'D':
            return DeadlineParser.parse(text);
        case 'E':
            return EventParser.parse(text);
        default:
            throw new DukeException(String.format("Cannot parse Task from \n\t`%s`", text));
        }
    }
}
