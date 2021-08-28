package duke.task;

//make abstract class?
public class Task {
    private boolean isDone;
    private String taskSummary;

    /**
     * Constructor for Task class.
     * Protected access modifier so that extending classes can call it.
     *
     * @param taskSummary description of task
     */
    protected Task(String taskSummary) {
        this.taskSummary = taskSummary;
        this.isDone = false;
    }

    /**
     * Factory method of Task class.
     * Takes in a String, parses it and returns the Task it represented
     *
     * @param storageLine string representing task
     * @return Task which the string represented
     */
    public static Task parseLine(String storageLine) {
        String taskSymbol = Character.toString(storageLine.charAt(0));
        return switch (taskSymbol) {
            case "T" -> ToDo.parse(storageLine);
            case "E" -> Event.parse(storageLine);
            case "D" -> Deadline.parse(storageLine);
            default -> throw new IllegalArgumentException("Should not enter here");
        };
    }

    /**
     * @return "x" or "" depending on whether task is complete
     */
    public String completeStatus() {
        return this.isDone ? "x" : "";
    }

    //setter
    public void markCompleted() {
        this.isDone = true;
    }

    //getters
    public boolean isCompleted() {
        return this.isDone;
    }

    public String getTaskSummary() {
        return this.taskSummary;
    }

    //only for purpose of polymorphism, change to abstract?
    public String toStorageFormat() {
        return String.format(
            "? | %d | %s ",
            this.isCompleted() ? 1 : 0,
            this.getTaskSummary()
        );
    }

    @Override
    public String toString() {
        return String.format("[GENERIC TASK] [%s] %s", this.completeStatus(),this.taskSummary);
    }
}
