public class Event extends Task {
    private String time;
    private String type = "E";

    public Event(String name, boolean done, String time) {
        super(name, done);
        this.time = time;
    }


    @Override
    public String log_record() {
        int state;
        if (this.done) {
            state = 1;
        } else {
            state = 0;
        }
        return "E , " + state + " , " + name + " , " + time;
    }


    @Override
    public String toString() {
        return "[E][" + this.getStatus() + "] " + this.name
                + "(at: " + time + ")";
    }
}
