package duke.tasks;

/**
 * Class to handle ToDo tasks
 */
public class ToDo extends Task {

    private ToDo(String description) {
        super(description, "T");
    }

    /**
     * Factory method to create a new ToDo.
     *
     * @param description Description of the event.
     * @return a new instance of Event.
     */
    public static ToDo create(String description) {
        return new ToDo(description);
    }
}
