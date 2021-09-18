package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * class for deadlines, a type of task, with an added deadline field
 */
public class Deadline extends Task {

    private String deadline;
    private LocalDate deadlineDate = null;
    private LocalTime deadlineTime = null;

    /**
     * Constructor for task that does not specify whether task is done
     * Sets done to false by default
     *
     * @param title name of the task
     * @param deadline deadline of the task
     */
    public Deadline(String title, String deadline) {
        super(title);
        this.parseDate(deadline);
    }

    /**
     * Constructor for task that specifies whether task is done
     * Used when tasks are loaded from storage
     *
     * @param title name of task
     * @param isDone whether task is done or not
     * @param deadline deadline of task
     */
    public Deadline(String title, Boolean isDone, String deadline) {
        super(title, isDone);
        this.parseDate(deadline);
    }

    public void updateDate(String deadline) {
        this.parseDate(deadline);
    }

    private String formatTime(String time) {
        if (time.length() > 4) {
            return time;
        }
        String newTime = time.substring(0, 2) + ":" + time.substring(2, 4);
        return newTime;
    }

    private void parseDate(String deadline) {
        try {
            String[] deadlineArray = deadline.trim().split(" ");
            LocalDate deadlineDate = LocalDate.parse(deadlineArray[0].trim());
            this.deadlineDate = deadlineDate;
            LocalTime deadlineTime = LocalTime.parse(formatTime(deadlineArray[1].trim()));
            this.deadlineTime = deadlineTime;
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        } catch (DateTimeParseException e) {
            this.deadline = deadline;
            this.deadlineDate = null;
            this.deadlineTime = null;
        }
    }

    private String formatMonth(int month) {
        switch (month) {
        case 1:
            return "Jan";
        case 2:
            return "Feb";
        case 3:
            return "Mar";
        case 4:
            return "Apr";
        case 5:
            return "May";
        case 6:
            return "Jun";
        case 7:
            return "Jul";
        case 8:
            return "Aug";
        case 9:
            return "Sep";
        case 10:
            return "Oct";
        case 11:
            return "Nov";
        case 12:
            return "Dec";
        default:
            return "";
        }
    }

    private String dateTimeString() {
        String dateString = this.formatMonth(this.deadlineDate.getMonthValue()) + " " +
            this.deadlineDate.getDayOfMonth() + " " + this.deadlineDate.getYear() + " ";
        String timeString = this.deadlineTime == null ? "" : " " + this.deadlineTime.toString();
        return dateString + timeString;
    }

    /**
     * Returns a string representation of the Deadline for displaying
     *
     * @return string representation of the Deadline for displaying
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return this.deadlineDate != null
                ? "[D][X] " + this.title + "(by: " + this.dateTimeString() + ")"
                : "[D][X] " + this.title + "(by: " + this.deadline + ")";
        } else {
            return this.deadlineDate != null
                ? "[D][ ] " + this.title + "(by: " + this.dateTimeString() + ")"
                : "[D][ ] " + this.title + "(by: " + this.deadline + ")";
        }
    }

    /**
     * Returns a string representation of the Deadline for saving
     *
     * @return string representation of the Deadline for saving
     */
    @Override
    public String saveString() {
        if (this.isDone) {
            return this.deadlineDate != null
                ? "D : 1 : " + this.title + " : " + this.deadlineDate.toString() + (this.deadlineTime == null ? "" : " " + this.deadlineTime.toString())
                : "D : 1 : " + this.title + " : " + this.deadline;
        } else {
            return this.deadlineDate != null
                ? "D : 0 : " + this.title + " : " + this.deadlineDate.toString() + (this.deadlineTime == null ? "" : " " + this.deadlineTime.toString())
                : "D : 0 : " + this.title + " : " + this.deadline;
        }
    }
}
