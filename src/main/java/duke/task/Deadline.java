package duke.task;

import duke.util.DukeDateTime;
import duke.exception.DukeException;

/**
 * Represents a <code>Task</code> with a due date.
 */
public class Deadline extends Task implements Timestampable {
    private final DukeDateTime dueDate;

    public Deadline(String name, DukeDateTime dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    public Deadline(String name, boolean isDone, DukeDateTime dueDate) {
        super(name, isDone);
        this.dueDate = dueDate;
    }

    /**
     * Parses its text representation.
     * @param text the text representation found
     * @return the corresponding <code>Deadline</code> object
     * @throws DukeException if the text representation cannot be parsed accurately
     */
    public static Deadline fromText(String text) throws DukeException {
        String[] deadlineDetails = text.split(" \\| ", 4);
        if (deadlineDetails.length < 4) {
            throw new DukeException(String.format("Cannot parse Deadline from \n\t`%s`", text));
        }
        boolean isDone = deadlineDetails[1].equals("X");
        String name = deadlineDetails[2];
        DukeDateTime dueDate = DukeDateTime.parseISO(deadlineDetails[3]);
        return new Deadline(name, isDone, dueDate);
    }

    @Override
    public String toText() {
        String[] props = new String[]{"D", super.getStatusIcon(), super.getName(), dueDate.toISO()};
        return String.join(" | ", props);
    }

    @Override
    public boolean onSameDayAs(DukeDateTime date) {
        return DukeDateTime.onSameDay(dueDate, date);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }
}
