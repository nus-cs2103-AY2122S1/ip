//make abstract class?
public class Task {
    private boolean isDone;
    private String taskSummary;

    Task(String taskSummary) {
        this.taskSummary = taskSummary;
        this.isDone = false;
    }

    public static Task parseLine(String line) {
        String taskSymbol = Character.toString(line.charAt(0));
        switch (taskSymbol) {
            case "T":
                return ToDo.parse(line);
            case "E":
                return Event.parse(line);
            case "D":
                return Deadline.parse(line);
            default:
                throw new IllegalArgumentException("Should not enter here");
        }
    }
    public void markCompleted() {
        this.isDone = true;
    }

    public String completeStatus() {
        return this.isDone ? "x" : "";
    }

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
            this.isCompleted() ? 1 : 0,this.getTaskSummary()
        );
    }

    @Override
    public String toString() {
        return String.format("[GENERIC TASK] [%s] %s", this.completeStatus(),this.taskSummary);
    }
}
