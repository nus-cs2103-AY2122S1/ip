package duke;

/**
 * General category
 */
public class Task {
    private String name;
    private Boolean completed = false;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Mark the task completed
     */
    public void doneTask() {
        this.completed = true;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String saveTask(){
        return toString();
    }

    public String getName() {
        return name;
    }

    public String toString() {
        if(completed){
            return "[X] " + name;
        }
        return "[ ] " + name;
    }
}
