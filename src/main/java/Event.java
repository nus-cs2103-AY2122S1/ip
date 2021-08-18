public class Event extends Task {
    private String time;

    public Event(String rawTime) throws SkeltalException {
        super(rawTime.split("/", 2)[0]);
        String[] procTime = rawTime.split("/", 2);
        if (procTime.length == 1) {
            throw new SkeltalException("OOPS! The description of an event cannot be empty!");
        }
        String time = procTime[1];
        time = "(" + time + ")";
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + time;
    }
}
