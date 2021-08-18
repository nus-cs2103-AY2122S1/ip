public class Event extends Task {
    private String when;
    public Event(String str1, String str2) throws DukeException {
        super(str1);
        this.when = str2;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + when + ")";
    }
}