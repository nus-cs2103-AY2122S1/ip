package duke.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class encapsulates a deadline.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class Deadline extends Task {
    private final String dueDate;

    /**
     * Instantiates a new Deadline task.
     *
     * @param name the subject of the task.
     * @param dueDate the due date.
     */
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    /**
     * Gets the due date of the deadline task in Date form.
     *
     * @return Date of deadline task,
     * null if due date does not match specified simple date format.
     */
    public Date getDate() {
        DateFormat inputFormat;
        Date date;

        if (dueDate.contains(",")) {
            inputFormat = new SimpleDateFormat("dd MMM yyyy, h.mm aa");
        } else {
            inputFormat = new SimpleDateFormat("dd MMM yyyy");
        }
        try {
            date = inputFormat.parse(dueDate);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    @Override
    public String formatToSave() {
        return String.format("D | %s | %s", super.formatToSave(), dueDate);
    }

    /**
     * String representation of a Deadline.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s\n    (by: %s)", super.toString(), dueDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deadline) {
            Deadline dl = (Deadline) o;
            return dl.getTaskName().equals(this.getTaskName()) && dl.dueDate.equals(this.dueDate);
        }
        return false;
    }
}