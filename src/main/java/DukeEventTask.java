/**
 * Encapsulates a task with that will occur at a specified time period.
 * It inherits from `DukeTask`.
 */
public class DukeEventTask extends DukeTask {
    private String time;

    private DukeEventTask(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Processes the input string to create an event task with an action and time.
     *
     * @param description The input task string by the user
     * @return a `DukeEventTask` containing an action description and time information
     */
    public static DukeEventTask createTask (String description) {
        // Remove the 'event' prefix
        String descriptionWithoutPrefix = description.substring(5);

        // Split the description into its action and time parts
        String[] splitPartsUsingAt = DukeTask.splitDescriptionAndTime(descriptionWithoutPrefix, "/at");

        return new DukeEventTask(splitPartsUsingAt[0], splitPartsUsingAt[1]);
    }

    /**
     * Formats the task in string form, displaying the task type, status, description and time.
     *
     * @return the task in a displayed string format
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }
}
