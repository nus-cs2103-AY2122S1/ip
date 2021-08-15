public class Event extends Task {

    private String name;
    private boolean completed;
    private String at;

    public Event(String name, boolean completed, String at) {
        super(name, completed);
        this.at = at;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[E][X] " + name + " (at:" + at + ")";
        } else {
            return "[E][ ] " + name + " (at:" + at + ")";
        }
    }
}
