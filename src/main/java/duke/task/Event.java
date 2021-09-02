package duke.task;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

import duke.main.Date;

/**
 * Represents tasks with specific timing.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class Event extends Task {
    private final String TASK_MARKER = "E";
    private final String TASK_KEYWORD = "event ";
    private final String AT_KEYWORD = "at ";
    private String taskDescription;
    private String eventDate;
    private Date dueDate;

    /**
     * Class constructor.
     *
     * @param description consisting of duke.task description and timing.
     */
    public Event(String description) throws DateTimeParseException {
        super();
        int startingIndex = description.indexOf(TASK_KEYWORD) + TASK_KEYWORD.length();
        int startOfTimeIndex = description.indexOf(AT_KEYWORD);
        taskDescription = description.substring(startingIndex, startOfTimeIndex - 1);
        eventDate = description.substring(startOfTimeIndex + AT_KEYWORD.length());
        this.dueDate = new Date(eventDate);
    }

    /**
     * Class constructor for loading tasks from storage file.
     *
     * @param eventDescription description of event duke.task.
     * @param dateOfTask       date of the event duke.task.
     * @throws ParseException due to improper date format.
     */
    public Event(String eventDescription, String dateOfTask) throws ParseException {
        taskDescription = eventDescription;
        dueDate = Date.convertDateStringToDate(dateOfTask);
    }

    /**
     * Print out the event duke.task,
     *
     * @return string format of the event duke.task,
     * consisting of the duke.task marker "E", duke.task description and duration of the event.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s (at: %s)", TASK_MARKER, super.toString(), taskDescription,
            dueDate.toString());
    }

    /**
     * Formats the duke.task in to the storage format.
     *
     * @return storage format of the duke.task.
     */
    public String formatToStore() {
        return String.format("%s | %s | %s | %s", TASK_MARKER, getStatusIcon() == " " ? 1 : 0,
            taskDescription, dueDate.toString());
    }

    /**
     * Returns duke.task marker.
     *
     * @return a one character string that is a marker for this duke.task.
     */
    public String getTaskMarker() {
        return TASK_MARKER;
    }

    /**
     * Checks if given datetime matches the tasks date time.
     *
     * @param dateString date to compare with in string form.
     * @return true if the duke.task date time matches the date time given.
     */
    @Override
    public boolean isSameDate(String dateString) {
        return this.dueDate.isSameDate(dateString);
    }
}
