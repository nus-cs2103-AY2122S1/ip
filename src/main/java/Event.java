public class Event extends Task {

    protected String at;
    protected String status;

    public Event(String name, String at) {
        super(name);
        this.at = at;
        this.status = "[E]" + super.status + "(at: " + at + ")";
    }

}
