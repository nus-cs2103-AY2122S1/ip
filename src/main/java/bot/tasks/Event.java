package Bot.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task{
    protected static String YMD_DATE_FORMAT = "yyyy/MM/dd";
    protected static String DMY_DATE_FORMAT = "dd/MM/yyyy";
    protected static String YMD_REGEX = "([0-9]{4})/([1-9]{1}|[0]{1}[1-9]{1}|[1]{1}[0-2]{1})/"
            + "([1-9]{1}|[0]{1}[1-9]{1}|[1]{1}[0-9]{1}|[2]{1}[0-9]{1}|[3]{1}[0-1]{1})";
    protected static String DMY_REGEX = "([1-9]{1}|[0]{1}[1-9]{1}|[1]{1}[0-9]{1}|[2]{1}[0-9]{1}|[3]{1}[0-1]{1})"
            + "/([1-9]{1}|[0]{1}[1-9]{1}|[1]{1}[0-2]{1})/([0-9]{4})";
    protected String timing;

    /**
     * Creates an Event with a specified description and timing.
     *
     * @param description The description of this Event.
     * @param timing The timing of this Event.
     */
    public Event(String description, String timing) {
        super(description);
        String[] dateAndTime = timing.trim().split(" ", 2);

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
                    this.timing = dateString + sdf.format(date);
                } else {
                    this.timing = dateString + dateAndTime[1];
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
                    this.timing = dateString + sdf.format(date);
                } else {
                    this.timing = dateString + dateAndTime[1];
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            this.timing = timing;
        }
    }

    /**
     * Returns this Event's timing.
     *
     * @return A string showing what the timing of this Event is.
     */
    public String getTiming() {
        return this.timing;
    }

    /**
     * Returns this task's status and description.
     *
     * @return A string showing what the task is, its description and its timing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timing + ")";
    }
}
