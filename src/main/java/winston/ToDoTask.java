package winston;

/**
 * A class extension of Task representing a ToDoTask.
 */
public class ToDoTask extends winston.Task {
    private final String type;
    
    /**
     * Constructor for ToDoTask.
     *
     * @param description is the string of the description of the given task.
     */
    public ToDoTask(String description, boolean isCompleted) {
        super(description, isCompleted);
        this.type = "T";
    }
    
    public ToDoTask(String description) {
        super(description, false);
        this.type = "T";
    }

    /**
     * Method to convert information from object instance into a different format to be saved.
     *
     * @return a string with the type, completion status, task description.
     */
    @Override
    public String saveFormat() {
        assert (type == "T");
        return this.type + "," + super.saveFormat();
    }

    /**
     *  Method to convert information from object instance into a more readable format.
     *
     * @return a string with the type, completion status, task description and due date.
     */
    @Override
    public String toString() {
        return "[" + this.type + "] " + super.toString();
    }
}