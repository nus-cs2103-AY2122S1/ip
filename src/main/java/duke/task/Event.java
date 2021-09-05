package duke.task;

import duke.exception.DukeException.FileNotFoundException;
import duke.parser.Parser;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Represents a possible type of task in our task list, known as an Event. This task has
 * additional information that supports adding deadlines in terms of dates and a specific
 * 24-hour time range.
 *
 * @author yeo-yiheng
 */
public class Event extends TaskList {
    private final String time;
    private LocalDate localDate;
    private String[] timeRange;
    private String startTime;
    private String endTime;

    /**
     * Creates an instance of an Event class, a subclass of our task list class.
     *
     * @param description the description of our task to be logged
     * @param time the time description of our task which contains the deadline and time range
     *             of this task
     * @param isExisting the boolean that distinguishes between an existing task loaded from
     *                   the task file or a newly added task during program execution
     */
    public Event(String description, String time, boolean isExisting) throws FileNotFoundException {
        super(description);
        this.time = time;
        if (!isExisting) {
            localDate = Parser.findDate(time);
            timeRange = Parser.findTimeRange(time);
            if (timeRange != null) {
                startTime = Parser.convertTime(timeRange[0]);
                endTime = Parser.convertTime(timeRange[1]);
            }
            try {
                FILE.saveTask(this); // Saves task to hard disk
            } catch (IOException e) {
                throw new FileNotFoundException();
            }
        } else {
            Parser.parseEventTime(time, localDate, startTime, endTime);
        }
    }

    /**
     * Returns the String representation of an Event task class.
     *
     * @return the String representation of an Event task class
     */
    @Override
    public String toString() {
        String status = this.getStatusIcon();
        assert status != null : "Status cannot be null";
        if (localDate == null) {
            return "[E]" + "[" + status + "] " + this.description
                    + "(" + time + ")";
        } else {
            String timeRange = startTime == null ? "" : " " + startTime + " - " + endTime;
            return "[E]" + "[" + status + "] " + this.description
                    + "(" + localDate.getDayOfMonth() + " " + localDate.getMonth()
                    + " " + localDate.getYear() + timeRange + ")";
        }
    }
}
