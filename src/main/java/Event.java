public class Event extends Task {

    private String at;
    public final String code = "E";

    public Event(String description, String at) throws DukeException{
        super(description);
        this.at = at;
    }

    public String getTimeAttr() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[" + code + "]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getCode() {
        return this.code;
    }
}