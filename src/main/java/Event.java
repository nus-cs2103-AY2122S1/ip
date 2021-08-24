public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String writeToFile() {
        String s = "D" + " | ";
        if (this.isDone) {
            s += "1";
        } else {
            s += "0";
        }
        s = s + " | " + description + " | " + at;
        return s;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}

