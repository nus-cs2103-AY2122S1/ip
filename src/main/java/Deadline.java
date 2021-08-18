public class Deadline extends Task{

    private final String deadline;

    /**
     * The constructor for a Deadlines Task.
     * @param taskDescription The name of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    /**
     * @return String representation of Deadlines task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
