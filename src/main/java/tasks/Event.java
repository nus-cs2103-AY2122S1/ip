package tasks;

public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    
    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + (this.isDone ? "[X] " : "[ ] ") + this.description + " (at: " + this.at + ")";
    }

    @Override
    public String toStorage() {
        return ("E%" + this.isDone + "%" + this.description + "%" + this.at + "\n");
    }
}
