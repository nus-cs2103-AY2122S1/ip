package bot.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String timing;

    /**
     * Creates a Deadline with a specified description and time limit.
     *
     * @param description The description of this Deadline.
     * @param timing The time limit of this Deadline.
     */
    public Deadline(String description, String timing) {
        super(description);
        String[] dateAndTime = timing.trim().split(" ", 2);

        if (Pattern.matches(YMD_REGEX, dateAndTime[0])) {
            try {
                setBy(timing, YMD_DATE_FORMAT);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (Pattern.matches(DMY_REGEX, dateAndTime[0])) {
            try {
                setBy(timing, DMY_DATE_FORMAT);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            this.timing = timing;
        }
    }

    /**
     * Returns this task's time limit.
     *
     * @return A string showing what the time limit of this Deadline is.
     */
    public String getTiming() {
        return this.timing;
    }

    /**
     * Returns a String representing this task that is shown to the user.
     *
     * @return A String representing this task.
     */
    @Override
    public String toListFormat() {
        return String.format("D | %d | %s | %s\n",
                this.getStatusIcon().equals("X") ? 1 : 0,
                this.getDescription(),
                this.getTiming());
    }

    /**
     * Returns this task's status and description.
     *
     * @return A string showing what the task is, its description and its time limit.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.timing + ")";
    }

    private void setBy(String by, String format) throws ParseException {
        String[] dateAndTime = by.trim().split(" ", 2);
        DateFormat format1 = new SimpleDateFormat(format);
        Date date = format1.parse(dateAndTime[0]);
        DateFormat format2 = new SimpleDateFormat("d MMMMM yyyy, ");
        String dateString = format2.format(date);
        if (dateAndTime.length == 1) {
            this.timing = dateString.split(",")[0];
        } else if (Pattern.matches("%04d", dateAndTime[1]) && !dateAndTime[1].contains("pm")
                && !dateAndTime[1].contains("am")) {
            int time = Integer.parseInt(dateAndTime[1]);
            date = new SimpleDateFormat("hhmm").parse(String.format("%04d", time));
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
            this.timing = dateString + sdf.format(date);
        } else {
            this.timing = dateString + dateAndTime[1];
        }
    }
}
