/**
 * Encapsulates a task with a deadline.
 * It inherits from `DukeTask`.
 */
public class DukeDeadlineTask extends DukeTask {
    private String deadline;

    private DukeDeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Processes the input string to create a deadline task with an action and deadline.
     *
     * @param description The input task string by the user
     * @return a `DukeDeadlineTask` containing an action description and deadline information
     */
    public static DukeDeadlineTask createTask (String description) {
        // Remove the 'deadline' prefix
        String descriptionWithoutPrefix = description.substring(8);

        // Split the description into its action and time parts
        String[] splitPartsUsingBy = DukeTask.splitDescriptionAndTime(descriptionWithoutPrefix, "/by");

        return new DukeDeadlineTask(splitPartsUsingBy[0], splitPartsUsingBy[1]);
    }

    /**
     * Formats the task in string form, displaying the task type, status, description and deadline.
     *
     * @return the task in a displayed string format
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
