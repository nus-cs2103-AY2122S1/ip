public class EventTask extends Task{
    private final String date;

    public EventTask(String content, boolean isDone, String date) {
        super(content, isDone);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.date);
    }
}
