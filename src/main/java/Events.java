public class Events extends Task {
    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")" ;
    }

    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + this.at;
    }
}
