public class Event extends Task {
    private String timePeriod;

    public Event(String taskname, String timePeriod) {
        super(taskname);
        this.timePeriod = timePeriod;
    }

    @Override
    public String toString() {
        return "Event: " + super.toString() + " (at" + this.timePeriod + ")";
    }
}
