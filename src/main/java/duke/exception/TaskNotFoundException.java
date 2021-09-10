package duke.exception;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("Sorry, no such task exists!");
    }
    
    public String toString() {
        return super.toString();
    }
}
