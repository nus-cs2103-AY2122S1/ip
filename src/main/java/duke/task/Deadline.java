package duke.task;

import duke.main.DukeException;
import duke.main.Parser;

import java.time.LocalDate;
import java.util.Objects;

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
        this.by = Parser.parseTime(before);
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
     */
    public Deadline(String desc, String before, boolean isCompleted) {
        this(desc, before);
        super.completed = isCompleted;
    }

    private static String extractDesc(String descAndTime) throws DukeException {
        if (descAndTime.equals("")) {
            throw new DukeException("\t☹ OOPS!!! Your deadline needs a description.\n");
        }
        return descAndTime.split(" by ")[0];
    }

    private static String extractTime(String descAndTime) throws DukeException {
        try {
            return descAndTime.split(" by ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\t☹ OOPS!!! You need to specify a time.\n");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Task.printTime(by) + ")";
    }

    /**
     * Generates a formatted String for storing Deadline.
     *
     * @return formatted String for storing Deadline.
     */
    @Override
    public String storageString() {
        return "D | " + super.completed + " | " + super.description + " | " + this.by;
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
