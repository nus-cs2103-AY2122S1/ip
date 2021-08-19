public class Deadline extends Task{

    private String taskName;
    public Deadline(String taskName) {
        super(taskName);
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        if (taskName.contains("/by")) {
            return "[D]" + super.toString().
                    replaceFirst("/", "(") + ")";
        } else {
            return "[D]" + super.toString() + " (unknown deadline)";
        }
    }
}
