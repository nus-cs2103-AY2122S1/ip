package winston;

public class ToDoTask extends winston.Task {
    private final String type;
    /**
     * Constructor for ToDoTask
     *
     * @param description is the string of the description of the given task
     */
    public ToDoTask(String description, boolean isCompleted) {
        super(description, isCompleted);
        this.type = "T";
    }
    
    public ToDoTask(String description) {
        super(description, false);
        this.type = "T";
    }
    
    @Override
    public String saveFormat() {
        return this.type + "," + super.saveFormat();
    }

    @Override
    public String toString() {
        return "[" + this.type + "] " + super.toString();
    }
}