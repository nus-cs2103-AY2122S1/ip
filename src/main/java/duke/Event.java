package duke;

import java.time.LocalDateTime;

public class Event extends TaskItem {
    protected boolean readFromFile = false;
    protected LocalDateTime byDateAndTime;
    protected String dateAndTimeInString;
    /**
     * Constructor for creating an duke.Event object.
     * @param description description of the event.
     * @param byDateAndTime date and time of the event.
     */
    public Event(String description, LocalDateTime byDateAndTime) {
        super(description);
        this.byDateAndTime = byDateAndTime;
        if (byDateAndTime.getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) {
            int hour = byDateAndTime.getHour();
            int minute = byDateAndTime.getMinute();
            this.dateAndTimeInString = "Today at " + hour + ":" + minute;
        } else {
            String timeOfDay;
            int hour = byDateAndTime.getHour();
            if (byDateAndTime.getHour() > 12) {
                timeOfDay = "PM";
                hour -= 12;
            } else if (byDateAndTime.getHour() == 12) {
                timeOfDay = "PM";
            } else {
                timeOfDay = "AM";
            }
            this.dateAndTimeInString = this.byDateAndTime.getDayOfWeek().toString() + " " + hour + timeOfDay;
        }
    }

    /**
     * returns the Event in a writable format to the data.txt file, such that it can be loaded
     * in, in the event that Duke is restarted.
     * @return a String representation of an Event.
     */
    @Override
    public String toFileString() {
        return "[E]" + super.toString() + "--" + byDateAndTime.toString();
    }

    /**
     * Overriden toString() method.
     * @return a String representation of an duke.Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.dateAndTimeInString + ")";
    }
}
