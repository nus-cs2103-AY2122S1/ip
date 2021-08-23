public class Event extends Task {
    private final String at;

    public Event(String title, String at) {
        super(title);
        this.at = at;
    }

    static String getClassRepr() {
        return "E";
    }

    public String getAt() {
        return at;
    }

    public String toString() {
        return "[" + getClassRepr() + "]" + super.toString() + " (at: " + at + ")";
    }
}
