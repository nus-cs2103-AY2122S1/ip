public class Todo extends Task{
    private static final String TYPE = "T";

    Todo(String content) {
        super(content.substring(5));
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                this.getStatus() ? "X" : " ", this.getContent());
    }

    @Override
    public String record() {
        return String.format("T | %s | %s",
                this.getStatus() ? "1" : "0", this.getContent());
    }

    public String getType() {
        return TYPE;
    }
}
