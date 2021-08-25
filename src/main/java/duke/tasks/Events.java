package duke.tasks;

public class Events extends Task {

    private String dateTimeAt;
    public Events(String description, String dateTimeAt, boolean isDone) {
        super(description, isDone);
        this.dateTimeAt = dateTimeAt;
    }
    @Override
    public String persistedDataStringFormat() {
        char isDone01 = this.isDone ? '1' : '0';
        return String.format("E,%c,%s,%s", isDone01, this.description, this.dateTimeAt);
    }
    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + " (%s:%s)", this.dateTimeAt.substring(0,2), this.dateTimeAt.substring(2));
    }
}
