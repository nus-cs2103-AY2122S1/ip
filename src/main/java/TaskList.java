import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String deleteTask(int index) {
        String taskString = taskList.get(index).toString();
        this.taskList.remove(index);
        return taskString;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public String markAsDone(int index) {
        Task task = taskList.get(index);
        task.markAsDone();
        return task.toString();
    }

    public String toStorageString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task nextTask = taskList.get(i);
            String nextString = nextTask.toStorageString() + "\n";
            result += nextString;
        }
        return result.trim();
    }

    public int getSize() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task nextTask = taskList.get(i);
            String nextTaskString = (i + 1) + "." + nextTask.toString() + "\n";
            result += nextTaskString;
        }

        result = result.trim();
        return result;
    }
}
