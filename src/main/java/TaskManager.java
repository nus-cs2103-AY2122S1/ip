import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public boolean addTask(Task task) {
        tasks.add(task);
        return true;
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    public boolean markAsDone(int taskNumber) {
        tasks.get(taskNumber - 1).markDone();
        return true;
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public boolean remove(int taskNumber) {
        tasks.remove(taskNumber - 1);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder();
        int numTasks = tasks.size();
        for (int i = 0; i < numTasks; i++) {
            listString.append(i + 1);
            listString.append(". ");
            listString.append(tasks.get(i).toString());
            if (i != numTasks - 1) {
                listString.append("\n");
            }
        }
        return listString.toString();
    }
}
