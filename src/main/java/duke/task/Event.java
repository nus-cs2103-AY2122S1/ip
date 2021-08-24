package duke.task;

public class Event extends Task {
    final private String at;

    public Event(String content, String at) {
        super(content);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    public String toStorageString() {
        String s1 = super.toStorageString();
        String s2 = String.format("E %s | %s", s1, at);
        return s2;
    }
}
