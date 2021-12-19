package duke;

import duke.exception.InvalidDeadlineException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A subclass of task that represents a deadline.
 *
 */
public class Deadline extends Task {
    private String type;
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;
    /**
     * Constructor for Deadline class.
     *
     * @param title Name of the task to be created.
     * @param deadline Date and time of deadline.
     */
    public Deadline(String title, String deadline) throws InvalidDeadlineException {
        super(title);
        type = "D";
        String[] dateTime = deadline.split(" ");
        if (dateTime.length < 1) {
            throw new InvalidDeadlineException();
        }

        try {
            deadlineDate = LocalDate.parse(dateTime[0]);
            if (dateTime.length == 1) {
                deadlineTime = LocalTime.parse("23:59");
            } else {
                deadlineTime = LocalTime.parse(dateTime[1]);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }

    @Override
    public String toString() {
        String date = this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String time = this.deadlineTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        String deadline = date + " " + time;
        return "[" + type + "]" + super.toString() + "(by: " + deadline + ")";
    }

    /**
     * A method that prints out details of a task.
     *
     * @return Details of task.
     */
    @Override
    public String writeTask() {
        String date = this.deadlineDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = this.deadlineTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        String deadline = date + " " + time;
        return type + " | " + super.writeTask() + " | " + deadline;
    }
}
