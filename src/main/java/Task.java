public class Task  {
    private static Task[] taskList = new Task[100];
    private static int counter = 0;

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void addTask() {
        Task.taskList[counter] = this;
        counter++;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static Task[] getTaskList() {
        return Task.taskList;
    }

    public static int getCounter() {
        return Task.counter;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}
