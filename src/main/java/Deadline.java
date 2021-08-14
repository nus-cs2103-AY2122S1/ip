public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder deadlineLine = new StringBuilder();
        if (this.isDone) {
            deadlineLine.append("[D][x]");
        } else {
            deadlineLine.append("[D][ ]");
        }
        deadlineLine.append(this.description.replaceFirst("deadline", "") + "(by:" + this.time + ")");
        return deadlineLine.toString();
    }
}
