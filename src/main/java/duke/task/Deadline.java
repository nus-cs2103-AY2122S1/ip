package duke.task;

import duke.exception.DukeException.FileNotFoundException;
import duke.parser.Parser;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Represents a possible type of task in our task list, known as a Deadline. This task has
 * additional information that supports adding deadlines in terms of dates and 24 hour time.
 *
 * @author yeo-yiheng
 */
public class Deadline extends TaskList {

    private final String time;
    private LocalDate localDate;
    private String deadLineTiming;

    /**
     * Creates an instance of a Deadline class, a subclass of our task list class.
     *
     * @param description the description of our task to be logged
     * @param time the time description of our task which contains the deadline of this task
     * @param isExisting the boolean that distinguishes between an existing task loaded from
     *                   the task file or a newly added task during program execution
     */
    public Deadline(String description, String time, boolean isExisting) throws FileNotFoundException {
        super(description);
        this.time = time;

        if (!isExisting) {
            localDate = Parser.findDate(time);
            deadLineTiming = Parser.findTime(time);
            if (deadLineTiming != null) {
                deadLineTiming = Parser.convertTime(deadLineTiming);
            }
            try {
                FILE.saveTask(this); // Saves task to hard disk
            } catch (IOException e) {
                throw new FileNotFoundException();
            }
        } else {
            Parser.parseDeadlineTime(time, localDate, deadLineTiming);
        }
    }

    /**
     * Returns the String representation of a deadline task class.
     *
     * @return the String representation of a deadline task class
     */
    @Override
    public String toString() {
        String status = this.getStatusIcon();
        assert status != null : "Status cannot be null";
        if (localDate == null) {
            return "[D]" + "[" + status + "] " + this.description + "(" + time + ")";
        } else {
            String endTime = deadLineTiming == null ? "" : " " + deadLineTiming;
            return "[D]" + "[" + status + "] " + this.description
                    + "(" + localDate.getDayOfMonth() + " " + localDate.getMonth()
                    + " " + localDate.getYear() + endTime + ")";
        }
    }
}
