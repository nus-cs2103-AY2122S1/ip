package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class that inherits from Entry to encapsulate Deadlines.
 */
public class Deadline extends Entry {

    private LocalDate deadline;

    /**
     * Constructor for Deadline.
     */
    public Deadline() {
        super();
        this.deadline = LocalDate.now();
    }

    /**
     * Constructor for Deadline.
     *
     * @param task Entry to be saved.
     * @param deadline Deadline of entry.
     * @throws DukeException Error thrown if deadline is in wrong format.
     */
    public Deadline(String task, String deadline) throws DukeException {
        super(task);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            this.deadline = LocalDate.now();
            throw new DukeException("Invalid timing format! Enter dates in yyyy-mm-dd format");
        }
    }

    /**
     * Overrides Entry's saveString method.
     * Returns string to be saved representing the Deadline.
     *
     * @return String to be saved representing the Deadline.
     */
    @Override
    public String saveString() {
        return "D" + super.saveString() + "," + this.deadline;
    }

    /**
     * Overrides Entry's toString method.
     * Returns String description of deadline.
     *
     * @return String representing Deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + "(by: " + this.deadline.format(formatter) + ")";
    }

    /**
     * Returns true if Deadline is Empty.
     *
     * @return Boolean corresponding to Deadline's length.
     */
    @Override
    public boolean isEmpty() {
        return super.isEmpty() && !deadline.isAfter(LocalDate.now());
    }
}
