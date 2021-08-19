public class Deadline extends Task {
    String type;
    String deadline;

    Deadline(String title, String deadline) {
        super(title);
        this.type = "[D]";
        this.deadline = deadline;
    }

    @Override
    String printTask() {
        return type + super.printTask() + "(by:" + deadline + ")";
    }
}
