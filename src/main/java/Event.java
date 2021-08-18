public class Event extends Task {
    protected String when;
    public Event(String str1, String str2) {
        super(str1);
        this.when = str2;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + when + ")";
    }
}