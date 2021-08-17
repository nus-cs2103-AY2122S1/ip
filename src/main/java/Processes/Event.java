package Processes;

public class Event extends Task {

    public Event(String description, String remarks) {
        super(description, remarks);
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[E][X] " + this.description + " (by: " + remarks + ")";
        }
        return "[E][ ] " + this.description + " (by: " + remarks + ")";
    }
}