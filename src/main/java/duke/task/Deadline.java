package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.util.DukeException;
import duke.util.Utility;

/**
 * A Task with a given finishing date.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Initialises a Deadline.
     *
     * @param description description of Deadline
     * @param by date of Deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }


    private static void checkFormat(String formattedString) throws DukeException {
        int byIndex = formattedString.indexOf("/by ");
        if (byIndex == -1) {
            byIndex = formattedString.length();
        }

        String keyword = formattedString.split(" ", 2)[0];

        if (!keyword.startsWith("deadline")) {
            throw new DukeException("I can't seem to find the deadline keyword");
        } else if (formattedString.length() <= 9
                || formattedString.substring(9, byIndex).isEmpty()) {
            //Checks for characters between "deadline " and "/by"
            throw new DukeException("the description of deadline cannot be empty");
        } else if (byIndex == formattedString.length()
                || formattedString.length() < byIndex + 5) {
            //Checks for characters after "/by"
            throw new DukeException("the [/by] time of deadline cannot be empty");
        }
    }

    /**
     * Creates a Deadline given a Deadline represented as a formatted string.
     * Format: deadline [description] /by [DD/MM/YYYY]
     *
     * @param formattedString Deadline represented as a formatted string.
     * @return Created Deadline
     * @throws DukeException given string fails to meet format requirements
     */
    public static Deadline create(String formattedString) throws DukeException {
        checkFormat(formattedString);

        int byIndex = formattedString.indexOf("/by");
        LocalDate time;
        try {
            time = LocalDate.parse(
                formattedString.substring(byIndex + 4),
                Utility.DATE_SHORT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("are you following the correct date format (DD/MM/YYYY)?");
        }

        return new Deadline(formattedString.substring(9, byIndex).trim(), time);
    }

    @Override
    public LocalDate getDate() {
        return by;
    }

    @Override
    public String toString() {
        char statusIcon = isDone ? '\u2713' : ' ';
        String timeString = Utility.DATE_MED_FORMATTER.format(this.by);

        return String.format("[%c] Deadline: %s (by: %s)",
                statusIcon, this.description, timeString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Deadline)) {
            return false;
        }

        Deadline deadline = (Deadline) o;
        return isDone == deadline.isDone
                && by.equals(deadline.by)
                && description.equals(deadline.description);
    }
}
