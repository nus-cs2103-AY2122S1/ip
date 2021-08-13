public class Event extends Task {

    String time;

    public Event(String input) {
        super(input.substring(0, input.indexOf("/at ") - 1));
        this.time = input.substring(input.indexOf("/at " + 4));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(by: " + time + ")";
    }
}
