package duke.tasks;

/**
 * Encapsulates a Todo type task
 */
public class Todo extends Task {
    private TaskType type = TaskType.TODO;
    public Todo (String description){
        super(description);
    }

    /**
     * Creates a new Todo task with description and isDone
     * @param description - the description of the task
     * @param isDone - determines if the task has been completed or not
     */
    public Todo (String description, boolean isDone, boolean isHighOrLow) {
        super(description, isDone, isHighOrLow);
    }

    @Override
    public String showTask(){
        return "[T][" + (isDone ? "✗" : " ") + "] " +  "["+ (isHighOrLow ? "High" : "Low") + " ] " + description;
    }

    @Override
    public String saveTask() {
        return "T | " + (isDone ? 1 : 0) + " | " + (isHighOrLow ? 1 : 0) + " | " + description;

    }
}


