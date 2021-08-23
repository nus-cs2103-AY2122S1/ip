public class Event extends Task{
    private String time;

    public Event(String name, String time) {
        super(name, "E");
        this.time = time;
    }

    public Event(String name, String time, boolean completed) {
        super(name, completed, "E");
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getSaveFormat() {
        return String.format("%s|%s", super.getSaveFormat(), this.time);
    }
}
