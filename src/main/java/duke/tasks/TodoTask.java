package duke.tasks;

/**
 * Class for deadline tasks that do not contain a
 * date or datetime
 */
public class TodoTask extends Task {

    /**
     * Constructor that initializes an EventTask object
     * @param description description of event
     */
    public TodoTask(String description) {
        super(description, TaskType.TODO);
    }

}
