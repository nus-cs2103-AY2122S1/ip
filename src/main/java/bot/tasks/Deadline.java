package Bot.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task{
    protected static String YMD_DATE_FORMAT = "yyyy/MM/dd";
    protected static String DMY_DATE_FORMAT = "dd/MM/yyyy";
    protected static String YMD_REGEX = "([0-9]{4})/([1-9]{1}|[0]{1}[1-9]{1}|[1]{1}[0-2]{1})/"
            + "([1-9]{1}|[0]{1}[1-9]{1}|[1]{1}[0-9]{1}|[2]{1}[0-9]{1}|[3]{1}[0-1]{1})";
    protected static String DMY_REGEX = "([1-9]{1}|[0]{1}[1-9]{1}|[1]{1}[0-9]{1}|[2]{1}[0-9]{1}|[3]{1}[0-1]{1})"
            + "/([1-9]{1}|[0]{1}[1-9]{1}|[1]{1}[0-2]{1})/([0-9]{4})";
    protected String by;

    /**
     * Creates a Deadline with a specified description and time limit.
     *
     * @param description The description of this Deadline.
     * @param by The time limit of this Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        String[] dateAndTime = by.trim().split(" ", 2);

        if (Pattern.matches(YMD_REGEX, dateAndTime[0])) {
            try {
                DateFormat format1 = new SimpleDateFormat(YMD_DATE_FORMAT);
                Date date = format1.parse(dateAndTime[0]);
                DateFormat format2 = new SimpleDateFormat("d MMMMM yyyy, ");
                String dateString = format2.format(date);
                if (Pattern.matches("%04d", dateAndTime[1]) && !dateAndTime[1].contains("pm") && !dateAndTime[1].contains("am")) {
                    int time = Integer.parseInt(dateAndTime[1]);
                    date = new SimpleDateFormat("hhmm").parse(String.format("%04d", time));
                    // Set format: print the hours and minutes of the date, with AM or PM at the end
                    SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
                    this.by = dateString + sdf.format(date);
                } else {
                    this.by = dateString + dateAndTime[1];
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (Pattern.matches(DMY_REGEX, dateAndTime[0])) {
            try {
                DateFormat format1 = new SimpleDateFormat(DMY_DATE_FORMAT);
                Date date = format1.parse(dateAndTime[0]);
                DateFormat format2 = new SimpleDateFormat("d MMMMM yyyy, ");
                String dateString = format2.format(date);
                if (Pattern.matches("%04d", dateAndTime[1]) && !dateAndTime[1].contains("pm") && !dateAndTime[1].contains("am")) {
                    int time = Integer.parseInt(dateAndTime[1]);
                    date = new SimpleDateFormat("hhmm").parse(String.format("%04d", time));
                    // Set format: print the hours and minutes of the date, with AM or PM at the end
                    SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
                    this.by = dateString + sdf.format(date);
                } else {
                    this.by = dateString + dateAndTime[1];
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            this.by = by;
        }
    }

    /**
     * Returns this task's time limit.
     *
     * @return A string showing what the time limit of this Deadline is.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns this task's status and description.
     *
     * @return A string showing what the task is, its description and its time limit.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
