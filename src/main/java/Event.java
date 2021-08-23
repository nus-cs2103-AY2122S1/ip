public class Event extends Task {

    private final String at;

    Event(String name, String at, boolean isCompleted) {
        super(name, TaskType.E, isCompleted);
        this.at = at;
    }

    private String happeningWhen() {
        return " (at: " + this.at + ")";
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public Task updateName(String input) {
        return new Event(input, this.at, this.getCompleted());
    }

    @Override
    public Task complete() {
        return new Event(this.getName(), this.at, true);
    }

    @Override
    public String details() {
        String checkbox = "[" + ( getCompleted() ? "X" : " ") + "]";
        String details = taskTypeString() + checkbox + " " + this.getName();
        return details + happeningWhen();
    }

    @Override
    public String getLabel() {
        return this.getName() + "|" + this.getAt();
    }
}
