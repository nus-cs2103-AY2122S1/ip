package duke.task;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

import duke.main.Date;
import duke.main.DukeException;

/**
 * Represents tasks with specific timing.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class Event extends Task {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String TASK_MARKER = "E";
    private String taskDescription;
    private String eventDate;
    private Date dueDate;
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String TASK_KEYWORD = "event ";
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
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
        eventDate = description.substring(startOfTimeIndex + AT_KEYWORD.length());
        try {
            this.dueDate = new Date(eventDate);
        } catch (DateTimeParseException e) {
            throw new DukeException(e);
        }
        assert !isDone : false;
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
        dueDate = Date.convertDateStringToDate(dateOfTask);
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
                dueDate.toString());
    }

    /**
     * Formats the task in to the storage format.
     *
     * @return storage format of the task.
     */
    public String formatToStore() {
        return String.format("%s | %s | %s | %s", TASK_MARKER, getStatusIcon() == " " ? 1 : 0,
                taskDescription, dueDate.toString());
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
        assert dueDate != null : "dueDate must not be unassigned";
        return this.dueDate.isSameDate(dateString);
    }
}
