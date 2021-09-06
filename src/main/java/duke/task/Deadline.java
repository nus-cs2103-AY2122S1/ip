package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends SingleTimedTask {


    /**
     * The constructor for the Deadline object.
     *
     * @param description The description of the Deadline
     * @param date The due date
     */
    public Deadline(String description, LocalDate date) {
        super(description, date);
        this.taskType = "D";
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += String.format(" (by: %s)", this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        return str;
    }

    @Override
    public String getDataString() {
        String str = super.getDataString();
        return String.format("%s_~_%s", str, this.date.format(DateTimeFormatter.ofPattern("d/MM/yyyy")));
    }
    @Override
    public boolean equals(Object object) {
        Deadline otherDeadline = (Deadline) object;
        return this.date.equals(otherDeadline.date)
                && this.getStatusIcon().equals(otherDeadline.getStatusIcon())
                && this.description.equals(otherDeadline.description)
                && this.taskType.equals(otherDeadline.taskType);
    }
}
