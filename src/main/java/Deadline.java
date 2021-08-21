import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class encapsulates a Deadline.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Deadline extends Task {
    private final Date deadline;
    private final String deadlineString;

    /**
     * Instantiates a new Deadline task.
     *
     * @param description the description of the deadline task.
     * @param deadline the deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadlineString = deadline;
        this.deadline = Parser.parseDateTime(deadline);
    }

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

    @Override
    public String convertToTxt() {
        return String.format("D | %s | %s", super.convertToTxt(), deadlineToString());
    }

    /**
     * String representation of a Deadline.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadlineToString());
    }
}
