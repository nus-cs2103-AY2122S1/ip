package duke;

public class Deadline extends Task {
    private String deadline;

    /**
     * Constructor for a Deadline object (Only takes in content as deadline is included in content).
     *
     * @param taskContent Content to be stored in the Deadline object.
     */
    public Deadline(String taskContent) {
        super(taskContent.split(" /by ")[0], "D");
        deadline = Parser.parseTiming(taskContent.split(" /by ")[1]);
        assert this.getType().equals("D") : "Wrong type";
    }

    /**
     * Constructor for a Deadline object.
     * Takes in content and deadline separately.
     *
     * @param taskContent Content to be stored in the Deadline object.
     * @param deadline Deadline of the Deadline object.
     */
    public Deadline(String taskContent, String deadline) {
        super(taskContent, "D");
        this.deadline = deadline;
        assert this.getType().equals("D") : "Wrong type";
    }

    /**
     * Returns the deadline of this Deadline object.
     *
     * @return deadline
     */
    @Override
    public String getTiming() {
        assert deadline != null: "Deadline cannot be null";
        return deadline;
    }

    /**
     * Returns the string representation of this Deadline object.
     *
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        if(super.isCompleted()) {
            return "[D][X] " + super.getTaskContent() + " " + "(by: " + deadline + ")";
        }else {
            return "[D][ ] " + super.getTaskContent() + " " + "(by: " + deadline + ")";
        }
    }
}
