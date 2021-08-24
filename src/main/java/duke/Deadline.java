package duke;

import java.time.LocalDateTime;

public class Deadline extends TaskItem {


    protected LocalDateTime byDateAndTime;


    protected String dateAndTimeInString;
    /**
     * Constructor for creating a duke.Deadline object.
     * @param description description of the task.
     * @param byDateAndTime the by-date of the task, e.g. "by 27-04-1999 12:00"
     */
    public Deadline(String description, LocalDateTime byDateAndTime) {
        super(description);
        this.byDateAndTime = byDateAndTime;
        if (byDateAndTime.getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) {
            int hour = byDateAndTime.getHour();
            int minute = byDateAndTime.getMinute();
            this.dateAndTimeInString = "Today at " + hour + ":" + minute;
        } else {
            this.dateAndTimeInString = this.byDateAndTime.getDayOfWeek().toString();
        }
    }

//    public duke.Deadline(String description) {
//        super(description);
//    }
    @Override
    public String toFileString() {
        return "[D]" + super.toString() + "--" + byDateAndTime.toString();
    }

    /**
     * Overriden toString() method.
     * @return a string representation of the duke.Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.dateAndTimeInString + ")";
    }
}
