package duke.tasks;
import duke.assignment.AssignmentType;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongEventOrDeadlineFormatException;
import duke.exceptions.WrongTimeFormatException;
import duke.date.Date;
import java.util.Optional;

/**
 * Class that specifies the properties of event task.
 */
public class Event extends Task {

    /**
     * Indicates the expected input of a
     * event task when input by user.
     */
    private static final String TASK_DIRECTIVE = "at";

    /**
     * Displays the type of task assignment of
     * event task in this case [E].
     */
    private static final String ASSIGNMENT_TYPE = AssignmentType.
            EVENT.
            getStatus();

    /**
     * Indicates whether event task has a date
     * and time element to it.
     */
    private static final boolean HAS_DATE_TIME = true;

    /**
     * Initializes event class with a description given by user.
     * @param desc String of user input.
     * @throws WrongDateFormatException if format of date in
     * event is wrong.
     * @throws WrongTimeFormatException if format of time in
     * event is wrong.
     */
    public Event(
            String desc) throws WrongDateFormatException,
            WrongTimeFormatException,
            WrongEventOrDeadlineFormatException {
        super(desc, HAS_DATE_TIME, TASK_DIRECTIVE);
    }

    /**
     * Initializes a new event class with a change of the completion status.
     * @param desc String of the user input
     * @param newStatus String updated status of the task
     * @param date Optional date if date exist
     */
    public Event(String desc, String newStatus, Optional<Date> date) {
        super(desc, newStatus, date);
    }

    /**
     * Returns new event task with
     * its completion status updated.
     * @param newStatus String new completion
     * status of the event task.
     */
    @Override
    public Event updateStatus(String newStatus) {
        return new Event(this.taskDescription, newStatus, this.date);
    }

    /**
     * Returns the string of the event
     * task to be stored in storage.
     */
    @Override
    public String getOriginalFormatForStorage() {
        return ASSIGNMENT_TYPE
                + this.status
                + " "
                + this.getOutputMessage(
                        this.date.
                                get().
                                getOriginalFormat());
    }

    @Override
    public String toString() {
        String outputString = this.getOutputMessage(
                this.date.
                        get().
                        toString());
        String assignmentProg = ASSIGNMENT_TYPE
                + this.status
                + " "
                + outputString;
        return assignmentProg;
    }

    private String getOutputMessage(String dateExpression) {
        StringBuilder sb = new StringBuilder("");
        String[] updatedDesc = this.taskDescription.split(" ");
        for (int i = 0; i < updatedDesc.length; i++) {
            String item = updatedDesc[i];
            if (item.contains("/" + TASK_DIRECTIVE)) {
                break;
            }
            sb.append(item + " ");
        }
        return sb.toString()
                + "("
                + TASK_DIRECTIVE
                + ": "
                + dateExpression
                + ")";
    }
}
