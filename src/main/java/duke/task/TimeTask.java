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
        editTime(time);
    }

    /**
     * Edits the time of a task to a given new Time
     *
     * @param newTime the given new Time.
     */
    public void editTime(String newTime) {

        // cannot have both timeDate and timeString containing values
        // or else addTime won't work properly
        try {
            this.timeDate = LocalDate.parse(newTime);
            this.timeString = null;
        } catch (DateTimeParseException e) {
            this.timeString = newTime;
            this.timeDate = null;
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
