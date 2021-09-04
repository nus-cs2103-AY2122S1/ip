package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The TimeTask class encapsulates Tasks with a time element, such as Deadlines and Events.
 */
public abstract class TimeTask extends Task {

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    private String timeString;
    private LocalDate timeDate;

    /**
     * Constructor for a TimeTask.
     *
     * @param name the given name of the TimeTask
     * @param time the given time of the TimeTask
     */
    public TimeTask(String name, String time) {
        super(name);
        try {
            this.timeDate = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            this.timeString = time;
        }
    }

    protected String generateToString(String taskInitial, String timePrefix) {
        String msg = super.generateToString(taskInitial) + " (" + timePrefix + ": ";
        msg = addTime(msg, true) + ")";
        return msg;
    }

    @Override
    protected String generatePrintToFile(String taskInitial) {
        String msg = super.generatePrintToFile(taskInitial) + super.DELIMITER;
        msg = addTime(msg, false);
        return msg;
    }

    private String addTime(String msg, boolean format) {
        String res = msg;
        if (timeString == null) {
            if (format) {
                res = res + formatDate(timeDate);
            } else {
                res = res + timeDate.toString();
            }
        } else {
            res = res + timeString;
        }
        return res;
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
