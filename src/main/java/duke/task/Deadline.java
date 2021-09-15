package duke.task;

import duke.DukeException;
import duke.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {

    protected LocalDate date;

    /**
     * Deadline constructor.
     *
     * @param description Description of deadline task.
     * @param date Date of deadline.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns a Deadline from string input with description and date.
     *
     * @param args String input with description and date in
     *                  '[description] (by: [MMM d yyyy])' format.
     * @return Deadline from string input with description and date.
     */
    public static Deadline build(String args) {
        String[] desc_date = new String[0];
        try {
            desc_date = Task.splitDescriptionAndDate(args, "by");
        } catch (DukeException e) {
            Ui.showLoadingError(e.getMessage());
        }
        String desc = desc_date[0];
        String dateString = desc_date[1];
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMM d yyyy"));
        return new Deadline(desc, date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Deadline)) {
            return false;
        }

        Deadline c = (Deadline) o;

        return c.description.equals(description) && c.date.equals(date);
    }
}