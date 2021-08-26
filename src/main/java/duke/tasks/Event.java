package duke.tasks;

import duke.exceptions.DeadlineException;
import duke.exceptions.EventException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent the task of type Event.
 */
public class Event extends Task {

    protected String at;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Creates an Event task object.
     *
     * @param description Description of what the Event task is about.
     * @param at Time of event.
     * @throws DeadlineException If the Event object cannot be created successfully due to empty description.
     */
    public Event(String description, String at) throws EventException {
        super(description);
        if(description.equals("event")) {
            throw new EventException();
        }
        this.at = at;
    }

    /**
     * Gets the information of the task such as its description and deadline to store in the hard disk.
     *
     * @return String that contains the information.
     */
    public String getInfo() {
        return getDescription() + "/" + this.at;
    }

    /**
     * Gets the type of the task and denotes it with a letter to store in the hard disk.
     *
     * @return The letter that indicates the type of the task.
     */
    public String getType() {
        return "E";
    };

    /**
     * Gets the date of the event.
     *
     * @return LocalDate object denoting the date of the event.
     */
    public LocalDate getDate() {
        String date = at.split(" ")[0];
        this.date = LocalDate.parse(date.split("/")[2] + "-"+ date.split("/")[1] + "-" + date.split("/")[0]);
        /*if(date.split("/").length != 0) {
            return LocalDate.parse()
        } else {
            return LocalDate.parse(date);
        }*/

        return this.date;
    }

    /**
     * Gets the start time of the event.
     *
     * @return LocalTime object denoting the end time of the event.
     */
    public LocalTime getStartTime() {
        String time = at.split(" ")[1];
        String startTime = time.split("-")[0];
        this.startTime = LocalTime.parse(startTime);
        return this.startTime;
    }

    /**
     * Gets the end time of the event.
     *
     * @return LocalTime object denoting the end time of the event.
     */
    public LocalTime getEndTime() {
        String time = at.split(" ")[1];
        String endTime = time.split("-")[1];
        this.endTime = LocalTime.parse(endTime);
        return this.endTime;
    }
    /**
     * Stores the task in the hard disk.
     *
     * @return String denoting the Event task which will be stored in the hard disk.
     */
    @Override
    public String storeTask() {
        return "E/" + getDone() + "/" + getDescription() + "/" + this.at;
    };

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the event task created.
     */
    @Override
    public String toString() {
        //return "[E]" + super.toString() + " (at: " + at + ")";
        return "[E]" + super.toString() + "(at: " + getDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " from " + getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")) + " to "
                + getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }
}
