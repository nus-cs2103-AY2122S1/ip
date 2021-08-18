public class Event extends Task {
    String time;

    public Event(String content, String time) {
        super(content);
        this.time = time;
    }

    public String toString() {
        return String.format("[E] [%s] %s (at: %s)",
                isDone ? "X" : " ", content, time);
    }
}

