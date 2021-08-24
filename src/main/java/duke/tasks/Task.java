package duke.tasks;
// Class that represents a Task
public class Task {
    private String description;
    private Boolean completed;
    
    // Constructor for a Task
    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    // Constructor for a completed Task
    public Task(String description, Boolean isComplete) {
        this.description = description;
        this.completed = isComplete;
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

    public String getFileRepr() {
        return " | " + (this.completed ? "1" : "0") + " | " + this.description;
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
