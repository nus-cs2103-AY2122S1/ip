import java.util.Arrays;

/**
 * A class to abstract a deadline which is a type of task with a date
 * to be completed by.
 */
public class Deadline extends Task {

    private final String deadline;

    private Deadline(String taskName, String deadline) {
        super(taskName, Type.DEADLINE);
        this.deadline = deadline;
    }

    /**
     * Creates a new deadline task based on the input by a user.
     * @param input An input in the form of a task name followed by the keyword "-by"
     *              then followed by the task deadline.
     * @return The newly created task deadline
     */
    public static Deadline newDeadlineTask(String input) {
        String[] inputArr = input.split("-by");
        String taskName = inputArr[0].trim();
        String completedBy = inputArr[1].trim();
        return new Deadline(taskName, completedBy);
    }

    @Override
    public String taskDescription() {
        return this.getTaskName() + " (by: " + this.deadline + ")";
    }
}
