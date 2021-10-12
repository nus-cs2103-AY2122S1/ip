package back;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate date;

    /**
     * Deadline constructor.
     *
     * @param taskName Name of deadline.
     * @param by Deadline of task.
     */
    public Deadline(String taskName, LocalDate by) {
        super(taskName);
        this.date = by;
    }

    /**
     * Returns if the date of this object matches then input date.
     * @param theirDate LocalDate object to be compared
     * @return boolean true if the LocalDate provided is the same.
     */
    public boolean queryIfDateEquals(LocalDate theirDate) {
        return theirDate.equals(date);
    }

    /**
     * Returns a string representation of this deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (DEADLINE due on: " + Ui.printDate(date) + ")";
    }

    /**
     * Returns if the object provided is the same as this.
     * @param o Object to be queried.
     * @return True if the object provided is the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline d = (Deadline) o;
            boolean nameEquals = d.taskName.equals(taskName);
            boolean donenessEquals = d.isDone == isDone;
            boolean dateEquals = d.date.equals(date);
            return (nameEquals && donenessEquals && dateEquals);
        } else {
            return false;
        }
    }
}
