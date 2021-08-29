package duke.task;

import duke.main.Date;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

/**
 * Represents tasks with deadline.
 * 
 * @author Gordon Yit
 * @Version CS2103T, Semester 1
 */
public class Deadline extends Task {
    private final String TASK_MARKER = "D";
    private String taskDescription;
    private String deadlineDate;
    private Date date;
    private final String KEYWORD = "deadline ";
    private final String BY = "by ";
    
    /**
     * Class constructor for Duke.Deadline class.
     * 
     * @param description consisting of task description and deadline date.
     */
    public Deadline(String description) throws DateTimeParseException {
        int startingIndex = description.indexOf(KEYWORD) + KEYWORD.length();
        int startOfTimingIndex = description.indexOf(BY);
        taskDescription = description.substring(startingIndex, startOfTimingIndex - 1);
        deadlineDate = description.substring(startOfTimingIndex + BY.length());
        date = new Date(deadlineDate);
    }

    /**
     * Class constructor for loading tasks from storage file.
     * 
     * @param deadlineDescription description of deadline task.
     * @param dateOfTask date of the deadline task.
     */
    public Deadline(String deadlineDescription, String dateOfTask) throws ParseException {
        taskDescription = deadlineDescription;
        date = Date.convertDateStringToDate(dateOfTask);
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
                                date.toString());
    }

    /**
     * Formats the task in to the storage format.
     *
     * @return storage format of the task.
     */
    public String formatToStore() {
        return String.format("%s | %s | %s | %s", TASK_MARKER, getStatusIcon() == " " ? 1 : 0, 
                                taskDescription, date.toString());
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
     * checks if given datetime matches the tasks date time.
     *
     * @param dateString date time (in string form) to compare with.
     * @return true if the task date time matches the date time given.
     */
    @Override
    public boolean isSameDate(String dateString) {
        return this.date.isSameDate(dateString);
    }
}
