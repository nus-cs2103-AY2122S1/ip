public class Event extends Task {
    private String time = "unknown";

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public static Event load(String[] loadData) {
        boolean done = loadData[1].equals("o");
        String name = loadData[2];
        String time = loadData[3];

        Event event = new Event(name, time);
        if (done) {
            event.doTask();
        }

        return event;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + time + ")";
    }

    @Override
    public String getSaveString() {
        return "e," + super.getSaveString() + "," + time;
    }
}
