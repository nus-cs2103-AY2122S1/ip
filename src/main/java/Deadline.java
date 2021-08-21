public class Deadline extends Task{
    private String time;
    private static final String TYPE = "D";

    Deadline(String content) {
        super(content.substring(9, content.indexOf("/")));
        this.time = content.substring(content.indexOf("/") + 1);
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.getStatus() ? "X" : " ", this.getContent(), this.time);
    }

    @Override
    public String record() {
        return String.format("D | %s | %s | %s",
                this.getStatus() ? "1" : "0", this.getContent(), this.time);
    }

    public String getType() {
        return TYPE;
    }
}
