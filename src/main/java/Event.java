public class Event extends Task {
    String time;
    public Event(String input) {
        super(input.split("/at")[0]);
        this.time = input.split("/at")[1];
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + String.format("(at:%s)", this.time));
    }
}
