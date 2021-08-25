package tasks;

public class Todo extends Task {

    public Todo(String desc, Boolean isDone) {
        super(desc, isDone);
    }

    /**
     * Provides the String representation of the task in the format
     * meant for writing to the file.
     *
     * @return The string representation of this deadline for the file to be saved to.
     */
    @Override
    public String saveText() {
        int isDone = this.isDone ? 1 : 0;
        return "T | " + isDone + " | " + desc + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + this.completionStatus() + desc;
    }
}
