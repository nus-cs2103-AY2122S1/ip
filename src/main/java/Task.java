import java.util.ArrayList;

public class Task  {
    private static ArrayList<Task> taskList = new ArrayList<Task>(100);
    private static int counter = 0;

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void addTask() {
        Task.taskList.add(this);
        counter++;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static ArrayList<Task> getTaskList() {
        return Task.taskList;
    }

    public static int getCounter() {
        return Task.counter;
    }

    public void deleteTask() {
        Task.taskList.remove(this);
        counter--;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

}
