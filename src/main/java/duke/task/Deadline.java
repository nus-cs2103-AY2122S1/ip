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

    private String dateDesc;
    private LocalDate date;

    /**
     * Constructor for Deadline.
     *
     * @param desc     Description of Task
     * @param dateDesc Deadline for Task
     */
    public Deadline(String desc, String dateDesc) {
        super(desc);
        parseDate(dateDesc);
    }

    /**
     * Constructor for Deadline.
     *
     * @param isDone   String representation of task being done
     * @param desc     Description of Task
     * @param dateDesc Deadline for Task
     */
    public Deadline(String isDone, String desc, String dateDesc) {
        super(isDone, desc);
        parseDate(dateDesc);
    }

    private void parseDate(String dateDesc) {
        Matcher m = Pattern.compile(REGEX).matcher(dateDesc);
        if (m.find()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                this.date = LocalDate.parse(m.group(1), formatter);
                this.dateDesc = dateDesc.replace(m.group(1), date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
            } catch (DateTimeParseException e) {
                this.dateDesc = dateDesc;
                this.date = null;
            }
        } else {
            this.dateDesc = dateDesc;
            this.date = null;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.dateDesc + ")";
    }

    @Override
    public String saveString() {
        return "D|" + super.saveString() + "|" + this.dateDesc;
    }
}
