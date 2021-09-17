package duke.data.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import duke.data.exception.DukeException;

/**
 * Class that represents a Deadline task.
 *
 * @author Wang Hong Yong
 */
public class Deadline extends Task {

    private final String by;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of the task.
     * @param by Date the task is due.
     * @param tags array of strings representing the tags.
     */
    public Deadline(String description, String by, String[] tags) {
        super(description);
        this.by = by;
        for (String tag: tags) {
            super.addTag(tag);
        }
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + parseStringToDate(by) + ")";
    }

    /**
     * Formats the deadline task to the desirable format.
     *
     * @return String representing the deadline task in the desirable format.
     */
    public String formatToWrite() {
        return String.format("D | %d | %s | %s", (super.isDone ? 1 : 0), this.by,
                super.formatToWrite());
    }

    /**
     * Formats the due date to the desirable format.
     *
     * @param dateTime Input date format.
     * @return String representing the date in the desirable format.
     */
    public String parseStringToDate(String dateTime) throws DukeException {
        DateTimeFormatter sourceFormat;
        DateTimeFormatter targetFormat;
        String convertedTime;
        try {
            if (dateTime.split("\\s+").length == 2) {
                sourceFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm");
                targetFormat = DateTimeFormatter.ofPattern("MMM dd uuuu hh:mm a", Locale.ENGLISH);
                convertedTime = LocalDateTime.parse(dateTime, sourceFormat)
                        .format(targetFormat);
            } else {
                sourceFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd");
                targetFormat = DateTimeFormatter.ofPattern("MMM dd uuuu", Locale.ENGLISH);
                convertedTime = LocalDate.parse(dateTime, sourceFormat)
                        .format(targetFormat);
            }
        } catch (Exception e) {
            throw new DukeException("Sorry there seems to be an error with your input.");
        }
        return convertedTime;

    }


}
