package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Task that stores date to do the task by.
 */
public class Deadline extends Task {
    private static final String REGEX = "(\\d{2}\\/\\d{2}\\/\\d{4})";

    private String dateDescription;
    private LocalDate date;
    private final String ORIGINAL_DESCRIPTION;

    /**
     * Constructor for Deadline.
     *
     * @param desc     Description of Task
     * @param dateDescription Deadline for Task
     */
    public Deadline(String desc, String dateDescription) {
        super(desc);
        ORIGINAL_DESCRIPTION = dateDescription;
        parseDate(dateDescription);
    }

    /**
     * Constructor for Deadline.
     *
     * @param isDone   String representation of task being done
     * @param desc     Description of Task
     * @param dateDescription Deadline for Task
     */
    public Deadline(String isDone, String desc, String dateDescription) {
        super(isDone, desc);
        ORIGINAL_DESCRIPTION = dateDescription;

        parseDate(dateDescription);
    }

    private void parseDate(String dateDescription) {
        Matcher m = Pattern.compile(REGEX).matcher(dateDescription);
        if (m.find()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                this.date = LocalDate.parse(m.group(1), formatter);
                this.dateDescription = dateDescription.replace(m.group(1), date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
            } catch (DateTimeParseException e) {
                this.dateDescription = dateDescription;
                this.date = null;
            }
        } else {
            this.dateDescription = dateDescription;
            this.date = null;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.dateDescription + ")";
    }

    @Override
    public String saveString() {
        return "D|" + super.saveString() + "|" + ORIGINAL_DESCRIPTION;
    }
}
