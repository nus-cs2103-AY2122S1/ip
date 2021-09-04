package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class encapsulates a Task to be done by a given deadline.
 */
public class Deadline extends Task {

    public static final String COMMAND_WORD = "deadline";
    public static final String KEYWORD = "/by";
    public static final String KEYWORD_WITH_SPACE = KEYWORD + " ";

    private String dlineString;
    private LocalDate dlineDate;

    /**
     * Constructor for a Deadline task.
     *
     * @param name the given name of the Deadline.
     * @param dline the given deadline of the Deadline.
     */
    public Deadline(String name, String dline) {
        super(name);
        try {
            this.dlineDate = LocalDate.parse(dline);
        } catch (DateTimeParseException e) {
            this.dlineString = dline;
        }
    }

    /**
     * Returns a String representing the Deadline Task.
     *
     * @return a String representing the Deadline Task.
     */
    @Override
    public String toString() {
        String msg = "[D]" + super.toString() + " (by: ";
        if (dlineString == null) {
            msg = msg + formatDate(dlineDate) + ")";
        } else {
            msg = msg + dlineString + ")";
        }
        return msg;
    }

    /**
     * Returns a String representing the Deadline Task to be saved in the taskList.txt file.
     *
     * @return a String representing the Deadline Task to be saved in the taskList.txt file.
     */
    public String printToFile() {
        String msg = "D | " + (this.getDone() ? 1 : 0) + " | " + this.getName() + " | ";
        if (dlineString == null) {
            msg = msg + dlineDate.toString();
        } else {
            msg = msg + dlineString;
        }
        return msg;
    }
}
