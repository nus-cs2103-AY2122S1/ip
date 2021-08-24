public class Deadline extends Task {
    String type;
    String deadline;

    Deadline(String title, String deadline) {
        super(title);
        this.type = "D";
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + "(by:" + deadline + ")";
    }

    @Override
    String writeTask() {
        return type + " | " + super.writeTask() + " | " + deadline;
    }
}
