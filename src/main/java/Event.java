public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description, Task.Type.E);
        this.time = time;
    }

    public Event(String description, String time, boolean isDone) {
        super(Task.Type.E, isDone, description);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + time + ")";
    }

    public String toFileString() {return super.toFileString() + " " + time;}
}
