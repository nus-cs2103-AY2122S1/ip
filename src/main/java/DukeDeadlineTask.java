/**
 * Encapsulates a task with a deadline.
 * It inherits from `DukeTask`.
 */
public class DukeDeadlineTask extends DukeTask {
    private static String TIME_SPLITTER = "/by";
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
    public static DukeDeadlineTask createTask (String description) throws InvalidTaskTimeFormatException {
        // Split the description into its action and time parts
        String[] splitPartsUsingBy = DukeTask.splitActionAndTime(
                description,
                DukeDeadlineTask.TIME_SPLITTER
        );

        try {
            return new DukeDeadlineTask(splitPartsUsingBy[0], splitPartsUsingBy[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskTimeFormatException(DukeActionTypeEnum.DEADLINE.toString(), DukeDeadlineTask.TIME_SPLITTER);
        }
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
