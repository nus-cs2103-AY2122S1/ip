package duke.task;

import java.text.ParseException;

import duke.main.Date;
import duke.main.DukeException;

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
    private Date dueDate;

    /**
     * Class constructor.
     *
     * @param description consisting of duke.task description and deadline date.
     */
    public Deadline(String description) throws DukeException {
        super();
        int startingIndex = description.indexOf(TASK_KEYWORD) + TASK_KEYWORD.length();
        int startOfTimingIndex = description.indexOf(BY_KEYWORD);
        taskDescription = description.substring(startingIndex, startOfTimingIndex - 1);
        String deadlineDate = description.substring(startOfTimingIndex + BY_KEYWORD.length());
        assert deadlineDate.contains("/") : "deadlineDate must be in the form dd/mm/yyyy";
        String[] dateComponents = deadlineDate.split("/");
        dueDate = new Date(dateComponents);
    }

    /**
     * Class constructor for loading tasks from storage file.
     *
     * @param deadlineDescription description of deadline duke.task.
     * @param dateOfTask          date of the deadline duke.task.
     * @throws ParseException due to improper date format.
     */
    public Deadline(String deadlineDescription, String dateOfTask) throws DukeException {
        taskDescription = deadlineDescription;
        dueDate = Date.convertDateStringToDate(dateOfTask);
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
     * @param dateString date time (in string form) to compare with.
     * @return true if the duke.task date time matches the date time given.
     */
    @Override
    public boolean isSameDate(String dateString) {
        assert dueDate != null : "dueDate must not be unassigned";
        return dueDate.isSameDate(dateString);
    }
}
