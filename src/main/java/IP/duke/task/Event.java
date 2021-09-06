package IP.duke.task;

import IP.duke.main.Date;
import IP.duke.main.DukeException;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

/**
 * Represents tasks with specific timing.
 * 
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class Event extends Task {
    private final String TASK_MARKER = "E";
    private String taskDescription;
    private Date eventDate;
    private final String TASK_KEYWORD = "event ";
    private final String AT_KEYWORD = "at ";
    
    /**
     * Class constructor.
     * 
     * @param description consisting of task description and timing.
     */
    public Event(String description) throws DukeException {
        super();
        int startingIndex = description.indexOf(TASK_KEYWORD) + TASK_KEYWORD.length();
        int startOfTimeIndex = description.indexOf(AT_KEYWORD);
        taskDescription = description.substring(startingIndex, startOfTimeIndex - 1);   
        String descriptionDate = description.substring(startOfTimeIndex + AT_KEYWORD.length());
        String[] dateComponent = descriptionDate.split("/");
        this.eventDate = new Date(dateComponent);
    }

    /**
     * Class constructor for loading tasks from storage file.
     *
     * @param eventDescription description of event task.
     * @param dateOfTask date of the event task.
     * @throws ParseException due to improper date format.
     */
    public Event(String eventDescription, String dateOfTask) throws DukeException {
        taskDescription = eventDescription;
        eventDate = Date.convertDateStringToDate(dateOfTask);
    }
    
    /**
     * Print out the event task,
     * 
     * @return string format of the event task, 
     * consisting of the task marker "E", task description and duration of the event.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s (at: %s)", TASK_MARKER, super.toString(), taskDescription,
            eventDate.toString());
    }

    /**
     * Formats the task in to the storage format.
     *
     * @return storage format of the task.
     */
    public String formatToStore() {
        return String.format("%s | %s | %s | %s", TASK_MARKER, getStatusIcon() == " " ? 1 : 0,
                taskDescription, eventDate.toString());
    }

    /**
     * Returns task marker. 
     *
     * @return a one character string that is a marker for this task.
     */
    public String getTaskMarker() {
        return TASK_MARKER;
    }

    /**
     * Checks if given datetime matches the tasks date time.
     * 
     * @param dateString date to compare with in string form.
     * @return true if the task date time matches the date time given.
     */
    @Override
    public boolean isSameDate(String dateString) {
        return this.eventDate.isSameDate(dateString);
    }
}
