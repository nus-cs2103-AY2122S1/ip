public class Event extends Task {
    private String time;

    Event(String content) {
        super(content.substring(6, content.indexOf("/")));
        this.time = content.substring(content.indexOf("/") + 1);
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                this.getStatus() ? "X" : " ", this.getContent(), this.time);
    }
}
