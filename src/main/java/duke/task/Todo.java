package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getTaskListOnDisk() {
        return "duke.task.Todo |" + super.getStatusIcon() + "| " + description;
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}