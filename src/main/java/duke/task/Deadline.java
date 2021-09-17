package duke.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.util.Parser;

/**
 * This class encapsulates a Deadline.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Deadline extends Task {
    private static final String DEADLINE_IDENTIFIER = "D";
    private final Date deadline;
    private final String deadlineString;

    /**
     * Instantiates a new Deadline task.
     *
     * @param description the description of the deadline task.
     * @param deadline    the deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadlineString = deadline;
        this.deadline = Parser.parseDateTime(deadline);
    }

    /**
     * Reformats the dateTime format into a more readable format to be displayed.
     * Returns the formatted string.
     *
     * @return Formatted string after reformatting.
     */
    private String deadlineToString() {
        if (deadline == null) {
            return deadlineString;
        }

        DateFormat outFormat;
        if (this.deadlineString.split(" ").length == 2) {
            outFormat = new SimpleDateFormat("MMM dd yyyy h.mm aa");
        } else {
            outFormat = new SimpleDateFormat("MMM dd yyyy");
        }

        return outFormat.format(this.deadline);
    }

    /**
     * Converts the deadline task into text format meant for persisted storage.
     * Returns the formatted string.
     *
     * @return Formatted string of task meant for persisted storage.
     */
    @Override
    public String convertToText() {
        return String.format(DEADLINE_IDENTIFIER + " | %s | %s", super.convertToText(), deadlineString);
    }

    /**
     * Checks whether the date and time user input is the same as the deadline of task.
     *
     * @param dateTime the date and time that the user input.
     * @return true if deadline of task is the same as date and time of user input.
     */
    public boolean isSameDateTime(String dateTime) {
        if (this.deadline == null) {
            return false;
        }

        return this.deadline.equals(Parser.parseDateTime(dateTime));
    }

    /**
     * String representation of a Deadline.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        return String.format("[" + DEADLINE_IDENTIFIER + "]%s (by: %s)", super.toString(), deadlineToString());
    }
}
