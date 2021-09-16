package duke.tasks;

import duke.exceptions.DeadlineException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent the task of type Deadline.
 */
public class Deadline extends Task {

    private String by;
    private LocalDate date;
    private LocalTime time;

    /**
     * Creates a Deadline task object.
     *
     * @param description Description of what the Deadline task is about.
     * @param by Deadline by which the task should be completed.
     * @throws DeadlineException If the Deadline object cannot be created successfully due to empty description.
     */
    public Deadline(String description, String by) throws DeadlineException {
        super(description);
        if(description.equals("deadline")) {
            throw new DeadlineException();
        }
        this.by = by;
    }

    /**
     * Gets the information of the task such as its description and deadline to store in the hard disk.
     *
     * @return String that contains the information.
     */
    public String getInfo() {
        return getDescription() + "/" + this.by;
    }

    /**
     * Gets the type of the task and denotes it with a letter to store in the hard disk.
     *
     * @return The letter that indicates the type of the task.
     */
    public String getType() {
        return "D";
    };

    /**
     * Gets the date that the task must be completed by.
     *
     * @return String denoting the deadline of the task.
     */
    public String getDate() {
        String date = by.split(" ")[0];
        this.date = LocalDate.parse(date.split("/")[2] + "-"+ date.split("/")[1] + "-" + date.split("/")[0]);
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return formattedDate;
    }

    /**
     * Gets the time by which the task must be completed.
     *
     * @return String denoting the time by which task must be completed.
     */
    public String getTime() {
        String time = by.split(" ")[1];
        this.time = LocalTime.parse(time);
        String formattedTime = this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
        return formattedTime;
    }

    /**
     * Stores the task in the hard disk.
     *
     * @return String denoting the Deadline task which will be stored in the hard disk.
     */
    @Override
    public String storeItem() {
        return "D/" + getDone() + "/" + getDescription() + "/" + this.by;
    };

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the deadline task created.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDate() + " " + getTime() + ")";
    }
}
