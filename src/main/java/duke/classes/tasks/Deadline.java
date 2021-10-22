package duke.classes.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task
 */
public class Deadline extends Task {
    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;
    private DateTimeFormatter printDateOut = DateTimeFormatter.ofPattern("MMM dd, E, yyyy");
    private DateTimeFormatter printTimeOut = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Class Constructor
     *
     * @param description Description of the Event
     * @param deadlineDate Time of the event
     */
    public Deadline(String description, LocalDate deadlineDate) {
        super(description, Task.Type.D);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Class Constructor
     *
     * @param description Description of the Event
     * @param deadlineDate Time of the event
     */
    public Deadline(String description, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(description, Task.Type.D);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Class Constructor when Event is read from localList.txt
     *
     * @param description Description of the Event
     * @param deadlineDate Date of the event
     * @param isDone Checks if the event is done
     */
    public Deadline(String description, String deadlineDate, boolean isDone) {
        super(description, Type.D, isDone);
        this.deadlineDate = LocalDate.parse(deadlineDate);
    }

    /**
     * Class Constructor when Event is read from localList.txt
     *
     * @param description Description of the Event
     * @param deadlineDate Date of the event
     * @param deadlineTime Time of the event
     * @param isDone Checks if the event is done
     */
    public Deadline(String description, String deadlineDate, String deadlineTime, boolean isDone) {
        super(description, Type.D, isDone);
        this.deadlineDate = LocalDate.parse(deadlineDate);
        this.deadlineTime = LocalTime.parse(deadlineTime);
    }

    @Override
    public String toString() {
        String temp = "";
        if (deadlineTime != null) {
            temp = " " + printTimeOut.format(deadlineTime);
        }
        return super.toString() + " (by: " + printDateOut.format(deadlineDate) + temp + ")";
    }

    /**
     * Method Returns class as a string in accordance to DukeStorage Format
     *
     * @return String in accordance to DukeStorage Format
     */
    public String toFileString() {
        String temp = "";
        if (deadlineTime != null) {
            temp = " " + deadlineTime + " T";
        }
        return super.toFileString() + " " + deadlineDate.toString() + temp;
    }
}
