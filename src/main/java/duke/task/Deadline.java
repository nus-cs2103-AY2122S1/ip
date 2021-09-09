package duke.task;

import java.text.ParseException;

import duke.main.DukeException;
import duke.main.TaskDate;


/**
 * Represents tasks with deadline.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 1
 */
public class Deadline extends Task {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String TASK_MARKER = "D";
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String TASK_KEYWORD = "deadline ";
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String BY_KEYWORD = "by ";
    private String taskDescription;
    private TaskDate dueDate;
    private String dateString;

    /**
     * Class constructor.
     *
     * @param description consisting of duke.task description and deadline date.
     */
    public Deadline(String description) throws DukeException {
        super();
        int startingIndex = calculateStartingIndex(description, TASK_KEYWORD);
        int startOfTimingIndex = calculateStartingIndex(description, BY_KEYWORD);
        taskDescription = getSubString(description, startingIndex, startOfTimingIndex - BY_KEYWORD.length());
        String deadlineDate = getSubString(description, startOfTimingIndex);
        String[] dayMonthYear = getDayMonthYear(deadlineDate);
        dueDate = new TaskDate(dayMonthYear);
        dateString = dueDate.toString();
    }

    /**
     * Class constructor for loading tasks from storage file.
     *
     * @param deadlineDescription description of deadline duke.task.
     * @param dateOfTask          date of the deadline duke.task.
     * @throws ParseException due to improper date format.
     */
    public Deadline(String deadlineDescription, String dateOfTask) throws DukeException {
        super();
        taskDescription = deadlineDescription;
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
     * Print out the deadline duke.task,
     *
     * @return string format of the deadline duke.task,
     * consisting of the duke.task marker "D", duke.task description and deadline of the duke.task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s (by: %s)", TASK_MARKER, super.toString(), taskDescription,
            dateString);
    }

    /**
     * Formats the duke.task in to the storage format.
     *
     * @return storage format of the duke.task.
     */
    public String formatToStore() {
        return String.format("%s | %s | %s | %s", TASK_MARKER, getStatusIcon() == " " ? 1 : 0,
            taskDescription, dateString);
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
     * @param dateString date time (in string form) to compare with.
     * @return true if the duke.task date time matches the date time given.
     */
    @Override
    public boolean isSameDate(String dateString) {
        return this.dueDate.isSameDate(dateString);
    }
}
