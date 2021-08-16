public class Event extends Task{
    private String time;

    public Event(String name, String time) {
        super(name, "E");
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
