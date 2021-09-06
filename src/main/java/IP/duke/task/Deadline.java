package IP.duke.task;

import IP.duke.main.Date;
import IP.duke.main.Duke;
import IP.duke.main.DukeException;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

/**
 * Represents tasks with deadline.
 * 
 * @author Gordon Yit
 * @version CS2103T, Semester 1
 */
public class Deadline extends Task {
    private final String TASK_MARKER = "D";
    private String taskDescription;
    private Date dueDate;
    private final String TASK_KEYWORD = "deadline ";
    private final String BY_KEYWORD = "by ";
    
    /**
     * Class constructor.
     * 
     * @param description consisting of task description and deadline date.
     */
    public Deadline(String description) throws DukeException {
        super();
        int startingIndex = description.indexOf(TASK_KEYWORD) + TASK_KEYWORD.length();
        int startOfTimingIndex = description.indexOf(BY_KEYWORD);
        taskDescription = description.substring(startingIndex, startOfTimingIndex - 1);
        String deadlineDate = description.substring(startOfTimingIndex + BY_KEYWORD.length());
        String[] dateComponents = deadlineDate.split("/");
        dueDate = new Date(dateComponents);
    }

    /**
     * Class constructor for loading tasks from storage file.
     * 
     * @param deadlineDescription description of deadline task.
     * @param dateOfTask date of the deadline task.
     * @throws ParseException due to improper date format.
     */
    public Deadline(String deadlineDescription, String dateOfTask) throws DukeException {
        taskDescription = deadlineDescription;
        dueDate = Date.convertDateStringToDate(dateOfTask);
    }
    
    /**
     * Print out the deadline task,
     * 
     * @return string format of the deadline task, 
     * consisting of the task marker "D", task description and deadline of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s (by: %s)", TASK_MARKER, super.toString(), taskDescription, 
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
     * @param dateString date time (in string form) to compare with.
     * @return true if the task date time matches the date time given.
     */
    @Override
    public boolean isSameDate(String dateString) {
        return this.dueDate.isSameDate(dateString);
    }
}
