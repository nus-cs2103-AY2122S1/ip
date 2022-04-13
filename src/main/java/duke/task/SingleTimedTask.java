package duke.task;

import java.time.LocalDate;

public abstract class SingleTimedTask extends Task {

    protected LocalDate date;

    /**
     * Constructor for the SingleTimedTask object.
     *
     * @param description Description of the SingleTimedTask
     * @param date Date of the SingleTimedTask
     */
    public SingleTimedTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public LocalDate getDate() {
        return this.date;
    }
}
