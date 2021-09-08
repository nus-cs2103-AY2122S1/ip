package bot.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
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
                setTiming(timing, YMD_DATE_FORMAT);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (Pattern.matches(DMY_REGEX, dateAndTime[0])) {
            try {
                setTiming(timing, DMY_DATE_FORMAT);
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
     * Returns a String representing this task that is shown to the user.
     *
     * @return A String representing this task.
     */
    @Override
    public String toListFormat() {
        return String.format("E | %d | %s | %s\n",
                this.getStatusIcon().equals("X") ? 1 : 0,
                this.getDescription(),
                this.getTiming());
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

    private void setTiming(String timing, String format) throws ParseException {
        String[] dateAndTime = timing.trim().split(" ", 2);
        DateFormat format1 = new SimpleDateFormat(format);
        Date date = format1.parse(dateAndTime[0]);
        DateFormat format2 = new SimpleDateFormat("d MMMMM yyyy, ");
        String dateString = format2.format(date);
        if (Pattern.matches("%04d", dateAndTime[1]) && !dateAndTime[1].contains("pm")
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
