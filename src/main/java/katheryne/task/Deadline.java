package katheryne.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String stringDueBy;
    protected LocalDate dateDueBy;

    // dummy constructor for Jackson
    public Deadline() {
        super();
    }

    /**
     * Creates a deadline task with a date due by and a description.
     *
     * @param description
     * @param dateDueBy
     */
    public Deadline(String description, LocalDate dateDueBy) {
        super(description);
        this.stringDueBy = dateDueBy.format(DateTimeFormatter.ofPattern("d MMM YYYY"));
        this.dateDueBy = dateDueBy;
    }

    // getters & setters (needed for jackson)

    public String getStringDueBy() {
        return stringDueBy;
    }

    protected void setDateDueBy(LocalDate dateDueBy) {
        this.dateDueBy = dateDueBy;
    }

    public LocalDate getDateDueBy() {
        return dateDueBy;
    }

    protected void setStringDueBy(String stringDueBy) {
        this.stringDueBy = stringDueBy;
    }

    @Override
    public String toString() {
        return "[Deadline] " + super.toString() + " (by: " + stringDueBy + ")";
    }

    /**
     * Checks if an object is equal to this deadline. It is equal if and only if it is a deadline with
     * the same description and due date
     *
     * @param obj Object to compare to this task.
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline o = (Deadline) obj;
            return this.getDateDueBy().equals(o.getDateDueBy()) && super.equals(obj);
        }
        return false;
    }
}
