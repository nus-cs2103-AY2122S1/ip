/**
 * tasks that need to be done before a specific date/time
 */
public class Deadline extends Task {
    private String endTime;
    
    public Deadline(String name, String endTime) {
        super(name);
        this.endTime = endTime; 
    }

    public String toString() {
        if (isDone) {
            return "[D][X] " + name + " (by: " + endTime + ")"; 
        } else {
            return "[D][ ] " + name + " (by: " + endTime + ")";
        }
    }
}