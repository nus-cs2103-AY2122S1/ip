public class Deadline extends Task{

    private String deadline;

    /**
     *  Constructor to create an DEADLINE task
     * @param name Name of task
     * @param deadline Deadline of task
     */
    Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns the name of the task in a format that shows type of task and its completion status
     * @return Task as a formatted string
     */
    @Override
    public String toString() {
        if(this.isDone()) {
            return String.format("[D][X] %s (by: %s)", this.getName(), this.deadline);
        } else {
            return String.format("[D][ ] %s (by: %s)", this.getName(), this.deadline);
        }
    }
}
