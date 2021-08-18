public class Event extends Task {
    private String startTime;

    public Event(String name, String startTime) {
        super(name);
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        String isDone = isDone() ? "x" : " ";
        return String.format("[E][%s] %s (at: %s)", isDone, getName(), getStartTime());
    }
}
