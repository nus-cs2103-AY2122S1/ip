public class Event extends Task {

    protected String at;

    public Event(String description, int number, String at) {
        super(description, number);
        this.at = at;
    }

    @Override
    public String getTask() {
        return number + "." + this.getTaskNoNum();
    }

    @Override
    public String getTaskNoNum() {
        return "[E]" + super.getTaskNoNum() + "(at: " + at + ")";
    }
}