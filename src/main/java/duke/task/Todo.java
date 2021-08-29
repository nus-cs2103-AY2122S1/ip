package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String taskListOnDisk() {
        return "duke.task.Todo |" + super.getStatusIcon() + "| " + description;
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}