package duke.parser;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.util.DukeDateTime;

public class DeadlineParser {
    /**
     * Parses a <code>Deadline</code> from its text representation.
     * @param text the text representation found
     * @return the corresponding <code>Deadline</code> object
     * @throws DukeException if the text representation cannot be parsed accurately
     */
    protected static Deadline parse(String text) throws DukeException {
        String[] deadlineDetails = text.split(" \\| ", 4);
        if (deadlineDetails.length < 4) {
            throw new DukeException(String.format("Cannot parse Deadline from \n\t`%s`", text));
        }
        boolean isDone = deadlineDetails[1].equals("X");
        String name = deadlineDetails[2];
        DukeDateTime dueDate = DukeDateTime.parseIso(deadlineDetails[3]);
        return new Deadline(name, isDone, dueDate);
    }
}
