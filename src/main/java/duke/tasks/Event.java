package duke.tasks;

import duke.exceptions.EventException;
import jdk.jshell.execution.LoaderDelegate;

import java.time.DateTimeException;
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
     * @throws EventException If the Event object cannot be created successfully due to empty description.
     */
    public Event(String description, String at) throws EventException, DateTimeException {
        super(description);
        if(description.equals("event")) {
            throw new EventException();
        }
        this.at = at;
        this.date = getDateObject();
        this.startTime = getStartTimeObject();
        this.endTime = getEndTimeObject();
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

    private LocalDate getDateObject() {
        String date = at.split(" ")[0];
        LocalDate dateObject = LocalDate.parse(date.split("/")[2] + "-"+ date.split("/")[1]
                + "-" + date.split("/")[0]);
        return dateObject;
    }
    /**
     * Gets the date of the event.
     *
     * @return String denoting the date of the event.
     */
    private String getDateInString() {
        //String date = at.split(" ")[0];
        //this.date = LocalDate.parse(date.split("/")[2] + "-"+ date.split("/")[1] + "-" + date.split("/")[0]);
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return formattedDate;
    }

    private LocalTime getStartTimeObject() {
        String time = at.split(" ")[1];
        String startTime = time.split("-")[0];
        LocalTime startTimeObject = LocalTime.parse(startTime);
        return startTimeObject;
    }


    /**
     * Gets the start time of the event.
     *
     * @return String denoting the end time of the event.
     */
    private String getStartTimeInString() {
        //String time = at.split(" ")[1];
        //String startTime = time.split("-")[0];
        //this.startTime = LocalTime.parse(startTime);
        String formattedStartTime = this.startTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return formattedStartTime;
    }

    private LocalTime getEndTimeObject() {
        String time = at.split(" ")[1];
        String endTime = time.split("-")[1];
        LocalTime endTimeObject = LocalTime.parse(endTime);
        return endTimeObject;
    }

    /**
     * Gets the end time of the event.
     *
     * @return String denoting the end time of the event.
     */
    private String getEndTimeInString() {
        String time = at.split(" ")[1];
        String endTime = time.split("-")[1];
        this.endTime = LocalTime.parse(endTime);
        String formattedEndTime = this.endTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return formattedEndTime;
    }
    /**
     * Stores the task in the hard disk.
     *
     * @return String denoting the Event task which will be stored in the hard disk.
     */
    @Override
    public String storeItem() {
        return "E/" + getDone() + "/" + getDescription() + "/" + this.at;
    };

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the event task created.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getDateInString() + " from " + getStartTimeInString()
                + " to " + getEndTimeInString() + ")";
    }
}
