public class Event extends Task {
    private String time;

    public Event(String rawTime) throws SkeltalException {
        super(rawTime.split("/", 2)[0]);
        String[] procTime = rawTime.split("/", 2);
        if (procTime.length == 1) {
            throw new SkeltalException("OOPS! The description of an event cannot be empty!");
        }
        String time = procTime[1];
        this.time = time;
    }

    public String formatTime() {
        String formatTime = "(" + this.time + ")";
        return formatTime;
    }

    @Override
    public String store() {
        return "E | " + super.store() + "| " + time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + formatTime();
    }
}
