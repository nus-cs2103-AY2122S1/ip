import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;
    private int totalTasks = 0;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public String addTask(Task task) {
        taskList.add(task);
        totalTasks++;
        return task.toString();
    }

    public String doTaskAtIndex(int taskNumber) {
        return taskList.get(taskNumber - 1).doTask();
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < totalTasks; i++) {
            listString.append(i + 1);
            listString.append(". ");
            listString.append(taskList.get(i).toString());
            if (i != totalTasks - 1) {
                listString.append("\n");
            }
        }
        return listString.toString();
    }
}
