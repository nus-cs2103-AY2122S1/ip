public class Event extends Task{

    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public static Event of(String args) {
        // parse args
        String[] parsedArgs = args.split(" /at ");
        return new Event(parsedArgs[0], parsedArgs[1]);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }
}
