public class Event extends Task{
    private String name;
    private boolean isDone;
    private int index;
    private String type;
    private String time;

    public Event(String name, boolean isDone, int index, String time) {
        super(name, isDone, index);
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
