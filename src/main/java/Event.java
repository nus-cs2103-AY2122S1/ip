public class Event extends Task {
    private String at;
    public Event(String input, String at) {
        super(input);
        this.at = at;
    }

    @Override
    public String formatTask() {
        return "E" + " | " + super.formatTask() + " | " + this.at;
    }

    @Override
    public  String toString() {
        return "[E] " + super.toString() + "(at: " + this.at + ")";
    }
}

