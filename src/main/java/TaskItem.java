public class TaskItem {

    private String task;
    private boolean completed;

    public TaskItem(String task) {
        this.task = task;
        this.completed = false; // by default if you add it to the list then it should be false first.
    }

    public String describeTaskItem() {
        return this.task;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void completeTask() {
        this.completed = true;
    }
}
