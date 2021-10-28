package duke.task;

import duke.main.DukeException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Encapsulates a task with a deadline.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructor for a Deadline.
     *
     * @param description of Deadline.
     * @param before      Time for Deadline.
     */
    public Deadline(String description, String before) {
        super(description);
        this.by = Task.parseTime(before);
    }

    /**
     * Constructor for a Deadline.
     *
     * @param descAndTime String containing description and time
     * @throws DukeException If extraction fails.
     */
    public Deadline(String descAndTime) throws DukeException {
        this(extractDesc(descAndTime), extractTime(descAndTime));
    }

    /**
     * Overloaded constructor for Deadline.
     *
     * @param desc        Description for Deadline.
     * @param before      Time for Deadline.
     * @param isCompleted indicates whether deadline is completed.
     * @param tags        List of tags associated with the Deadline.
     */
    public Deadline(String desc, String before, boolean isCompleted, List<String> tags) {
        this(desc, before);
        super.isComplete = isCompleted;
        super.tags = tags;
    }

    private static String extractDesc(String descAndTime) throws DukeException {
        String rawDesc = descAndTime.split(" by ")[0];
        String trimmedDesc = rawDesc.trim();
        if (trimmedDesc.equals("")) {
            throw new DukeException("OOPS!!! Your deadline needs a description.\n");
        }
        return trimmedDesc;
    }

    private static String extractTime(String descAndTime) throws DukeException {
        try {
            String rawTime = descAndTime.split(" by ")[1];
            String trimmedTime = rawTime.trim();
            return trimmedTime;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! You need to specify a time.\n");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "\n\t(by: " + Task.printTime(by) + ")\n";
    }

    /**
     * Generates a formatted String for storing Deadline.
     *
     * @return formatted String for storing Deadline.
     */
    @Override
    public String generateStorageString() {
        return "D | " + super.isComplete + " | " + super.description + " | " + super.formatTags() + " | " + this.by;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Deadline deadline = (Deadline) o;
        return by.equals(deadline.by);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), by);
    }
}
