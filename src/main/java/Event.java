public class Event extends Task {

    private final String at;

    public Event(String name, String at) {
        super(name, TaskType.E);
        this.at = at;
    }

    private String happeningWhen() {
        return " (at: " + this.at + ")";
    }

    @Override
    public String details() {
        return super.details() + happeningWhen();
    }
}
