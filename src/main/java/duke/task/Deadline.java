package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for a Deadline
     * @param description of Deadline
     * @param by Due Date of deadline in YYYY-MM-DD format
     */
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
     * Updates the Deadline based on the given updated data.
     * @param updatedData is in the form {description}/{YYYY-MM-DD}
     */
    @Override
    public void update(String updatedData) {
        assert updatedData.split("/").length == 2 : "2 fields to update for deadlines";
        String[] updatedFields = updatedData.split("/");
        this.description = updatedFields[0];
        this.by = LocalDate.parse(updatedFields[1]);
    };

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
