public class Task {
    private String taskName;
    private Boolean completed = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void doneTask() {
        this.completed = true;
    }

    public String toString() {
        if(completed){
            return "[X] " + taskName;
        }
        return "[ ] " + taskName;
    }
}
