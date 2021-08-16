public class Task {
    // task: can add task, list of tasks, can print tasks, can check done tasks
    // TaskList
    // one task
    // then outside can contain a list of task object 
    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.description;
    }
}