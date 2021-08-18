public class Event extends Task{
    private String at;

    Event(String content, String at){
        super(content);
        this.at = at;
    }

    public String toString() {
        return "[D]" + super.toString() + " (at:" + at + ")";
    }
}
