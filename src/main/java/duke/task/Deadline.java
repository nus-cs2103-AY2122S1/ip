package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.util.DukeException;

/**
 * Represents a deadline task with a <code>description</code> corresponding to the content
 * and a <code>by</code> time representing the deadline time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private Boolean hasTime = true;

    /**
     * Represents a new Deadline Object.
     * @param description for Deadline
     * @param by time for Deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseTime(by);
    }

    /**
     * Gets a By time of a Deadline object.
     *
     * @return by time of type LocalDateTime
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Parses a time String into a LocalDateTime.
     *
     * @param time a String of format dd/mm/yyyy hhmm(optional)
     * @return transformed time of type LocalDateTime
     */
    private LocalDateTime parseTime(String time) {
        String[] str = time.split(" ");
        String[] oldDate = str[0].split("/");
        if (oldDate.length != 3) {
            throw new DukeException("OOPS!!! The time is not of the correct format!");
        }
        LocalDateTime localTime;
        if (str.length > 1) {
            String hour = str[1].substring(0, 2);
            String min = str[1].substring(2, 4);

            localTime = LocalDateTime.of(Integer.parseInt(oldDate[2]), Integer.parseInt(oldDate[1]),
                    Integer.parseInt(oldDate[0]), Integer.parseInt(hour), Integer.parseInt(min));
        } else {
            localTime = LocalDate.of(Integer.parseInt(oldDate[2]), Integer.parseInt(oldDate[1]),
                    Integer.parseInt(oldDate[0]))
                    .atStartOfDay();
            hasTime = false;
        }
        return localTime;
    }


    @Override
    public String toString() {
        String string = "[D]" + super.toString() + " (by: ";
        if (hasTime) {
            string += by.format(DateTimeFormatter.ofPattern("HH:mm, dd/MM/yyyy")) + ")";
        } else {
            string += by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
        }
        return string;
    }

}
