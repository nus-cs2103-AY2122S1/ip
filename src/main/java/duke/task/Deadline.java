package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a comma separated string representation of the DeadLine's data
     * for data storage.
     *
     * @return String that represents the DeadLine's data
     */
    @Override
    public String getData() {
        return String.format("D,%s,%s", this.by, super.getData());
    }


    /**
     * Returns the string representation of a Deadline.
     *
     * @return String representing a Deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), by.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
    }
}
