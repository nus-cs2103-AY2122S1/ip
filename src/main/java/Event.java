public class Event extends Task {

    String time;

    public Event(String input) {
        super(input.substring(0, input.indexOf("/at ") - 1));
        this.time = input.substring(input.indexOf("/at ") + 4);
    }

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }

    public String printToFile() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + this.name + " | " + this.time;
    }
}
