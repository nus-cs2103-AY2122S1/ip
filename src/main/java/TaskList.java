import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        Task deleteTask = tasks.get(index);
        tasks.remove(index);
        return deleteTask;
    }

    public Task doneTask(int index) {
        Task doneTask = tasks.get(index);
        doneTask.markAsDone();
        return doneTask;
    }

    @Override
    public String toString() {
        String taskList = "";
        int count = 1;
        for (Task t : tasks) {
            taskList = taskList + count + "." + t + "\n";
            count += 1;
        }
        return taskList;
    }
}
