package duke.tasks;
import duke.assignment.AssignmentType;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongTimeFormatException;
import duke.date.Date;
import java.util.Optional;

/**
 * Class that specifies the properties of todo task
 */
public class ToDo extends Task {
    private static final String assignmentType = AssignmentType.TODO.getStatus();
    private static final boolean hasDateTime = false;

    /**
     * Initializes todo class with a description given by user
     * 
     * @param desc String of user input
     * @throws WrongDateFormatException if format of date in todo is wrong
     * @throws WrongTimeFormatException if format of time in todo is wrong
     */
    public ToDo(String desc) throws WrongDateFormatException, WrongTimeFormatException {
        super(desc, hasDateTime, "");
    }

    /**
     * Initializes a new todo class with a change of the completion status
     * 
     * @param desc String of the user input
     * @param newStatus String updated status of the task
     * @param date Optional date if date exist
     */
    public ToDo(String desc, String newStatus, Optional<Date> date) {
        super(desc, newStatus, date);
    }
    
    /**
     * returns the string of the todo task to be stored in storage
     */
    @Override
    public String getOriginalFormatForStorage() {
        return this.toString();
    }

    /**
     * returns new todo task with its completion status updated
     * 
     * @param newStatus String new completion status of the todo task
     */
    @Override
    public ToDo updateStatus(String newStatus) {
        return new ToDo(this.taskDescription, newStatus, this.date);
    }


    @Override
    public String toString() {
        String assignmentProg = assignmentType + this.status + " " + this.taskDescription;
        return assignmentProg;
    }

}
