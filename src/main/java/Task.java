// Class that represents a Task
public class Task {
    private String description;
    private Boolean completed;
    
    // Constructor for a Task
    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    // Mark a task as complete
    public void completeTask() {
        this.completed = true;
    }

    // Get status of task
    public Boolean isCompleted() {
        return this.completed;
    }

    // Get description of task
    public String getDescription() {
        return this.description;
    }

    // String representation of a Task
    @Override
    public String toString() {
        if (this.completed) {
            return ("[X] " + this.description);
        } else {
            return ("[ ] " + this.description);
        }
    }
}
