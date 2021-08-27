package virushade.tasks;

import virushade.StringManipulator;

public class Deadline extends Task {

    private final String deadline;

    /**
     * The constructor for a Deadlines Task.
     * @param taskDescription The name of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String taskDescription, String deadline, boolean isDone) {
        super(taskDescription, isDone);
        this.deadline = StringManipulator.convertDateAndTime(deadline);
    }

    /**
     * @return String representation of Deadlines task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " /by: " + deadline;
    }
}
