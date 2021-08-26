import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public Integer getNumberOfTasks() {
        return this.tasks.size();
    }
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(Integer taskNumber) {
        System.out.println("IM IN REMOVING");
        System.out.println(taskNumber);
        System.out.println(this.tasks);
        this.tasks.remove(taskNumber);
        System.out.println(this.tasks);
    }

    public Task getTask(Integer taskNumber) {
        return this.tasks.get(taskNumber);
    }
}
