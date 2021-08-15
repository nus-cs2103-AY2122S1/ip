public class Deadline extends Task {
    public Deadline(String taskDetails) {
        super(taskDetails);
    }
    public String toString() {
        return String.format("[D]%s", super.toString());
    }
}
