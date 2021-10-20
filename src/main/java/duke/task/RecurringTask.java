package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;

/**
 * Task that stores date to do the task by.
 */
public class RecurringTask extends Task {
    private static final String REGEX = "(\\d{2}\\/\\d{2}\\/\\d{4})";

    private LocalDate date;
    private int repeatDays;

    /**
     * Constructor for Deadline.
     *
     * @param desc     Description of Task
     * @param dateDescription Deadline for Task
     */
    public RecurringTask(String desc, String dateDescription, String repeat) throws DukeException {
        super(desc);
        this.repeatDays = parseRepeat(repeat);
        parseDate(dateDescription);
    }

    /**
     * Constructor for Deadline.
     *
     * @param isDone   String representation of task being done
     * @param desc     Description of Task
     * @param dateDescription Deadline for Task
     */
    public RecurringTask(String isDone, String desc, String dateDescription, String repeat) throws DukeException {
        super(isDone, desc);
        this.repeatDays = parseRepeat(repeat);
        parseDate(dateDescription);
    }

    private int parseRepeat(String repeat) throws DukeException {
        try {
            return Integer.parseInt(repeat);
        } catch (NumberFormatException e) {
            throw new DukeException(repeat + " cannot be converted to a number.");
        }
    }

    private void parseDate(String dateDescription) throws DukeException {
        Matcher m = Pattern.compile(REGEX).matcher(dateDescription);
        if (m.find()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                this.date = LocalDate.parse(m.group(1), formatter);
            } catch (DateTimeParseException e) {
                throw new DukeException("Date format is not given in dd/MM/yyyy format");
            }
        } else {
            throw new DukeException("Date format is not given in dd/MM/yyyy format");
        }
    }

    @Override
    public void markDone() {
        date = date.plusDays(repeatDays);
    }


    @Override
    public String toString() {
        return "[R]" + super.toString() + "(at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " repeat: " + this.repeatDays + ")";
    }

    @Override
    public String saveString() {
        return "R|" + super.saveString() + "|" + this.date.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))
                + "|" + this.repeatDays;
    }
}
