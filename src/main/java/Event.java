public class Event extends Task {
    String time;

    public Event(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E][%c] %s (at: %s)", isDone ? 'X' : ' ', description, time);
    }
}
