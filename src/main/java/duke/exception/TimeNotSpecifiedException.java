package duke.exception;

/**
 * Thrown to indicate that the time has not been provided by the user
 * when attempting to add a Deadline/Event to the task list.
 */
public class TimeNotSpecifiedException extends DukeException {

    /**
     * Constructs a TimeNotSpecifiedException with an argument
     * indicating the specified preposition (by/at).
     *
     * @param taskPreposition The preposition before the date/time.
     */
    public TimeNotSpecifiedException(String taskPreposition) {
        super("The date/time is not specified!\n"
                + "Please follow this format: [Task]" + taskPreposition + "[Date/Time]");
    }
}
