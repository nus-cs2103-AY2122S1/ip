/**
 * tasks without any date/time attached to it
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public String toString() {
        if (isDone) {
            return "[T][X] " + name; 
        } else {
            return "[T][ ] " + name; 
        }
    }
}
