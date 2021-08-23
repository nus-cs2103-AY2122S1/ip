package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Description:
 * Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm.
 *
 * @author Leong Hong Fai
 */

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String name, String date) {
        super(name);
        this.date = LocalDate.parse(date);
    }

    /**
     * Simple string representation of Deadline.
     *
     * @return A string consisting of the information of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}