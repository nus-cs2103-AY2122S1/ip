package duke.tasks;

/**
 *  The todo class is a subclass of Task representing a todo task
 */
public class ToDo extends Task {

    /**
     * public constructor which initialises the description of a todo
     * @param description description of a task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return this.description;
    }


    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}