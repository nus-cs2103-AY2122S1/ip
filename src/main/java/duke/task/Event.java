package duke.task;

import java.text.ParseException;

import duke.main.DukeException;
import duke.main.TaskDate;

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
    private TaskDate dueDate;
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String TASK_KEYWORD = "event ";
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String AT_KEYWORD = "at ";
    private String dateString;
    /**
     * Class constructor.
     *
     * @param description consisting of task description and timing.
     */
    public Event(String description) throws DukeException {
        super();
        int startingIndex = calculateStartingIndex(description, TASK_KEYWORD);
        int startOfTimeIndex = calculateStartingIndex(description, AT_KEYWORD);
        taskDescription = getSubString(description, startingIndex, startOfTimeIndex - AT_KEYWORD.length());
        eventDate = getSubString(description, startOfTimeIndex);
        String[] dayMonthYear = getDayMonthYear(eventDate);
        dueDate = new TaskDate(dayMonthYear);
        dateString = dueDate.toString();
    }
    /**
     * Class constructor for loading tasks from storage file.
     *
     * @param eventDescription description of event task.
     * @param dateOfTask date of the event task.
     * @throws ParseException due to improper date format.
     */
    public Event(String eventDescription, String dateOfTask) throws DukeException {
        super();
        taskDescription = eventDescription;
        dueDate = TaskDate.convertDateStringToDate(dateOfTask);
        dateString = dueDate.toString();
    }
    private int calculateStartingIndex(String description, String wordSlicer) {
        return description.indexOf(wordSlicer) + wordSlicer.length();
    }
    private String getSubString(String taskDescription, int startIndex, int ... endIndex) {
        if (endIndex != null) {
            return taskDescription.substring(startIndex, endIndex[0]);
        }
        return taskDescription.substring(startIndex);
    }
    private String[] getDayMonthYear(String date) {
        return date.split("/");
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
        return this.dueDate.isSameDate(dateString);
    }
}
