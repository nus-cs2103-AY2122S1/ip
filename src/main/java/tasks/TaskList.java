package tasks;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String getFormattedData() {
        StringBuilder data = new StringBuilder();

        int len = tasks.size();

        for (int i = 0; i < len - 1; i ++) {
            data.append(tasks.get(i).getFormattedData() + "\n");
        }

        data.append(tasks.get(len - 1).getFormattedData());

        return data.toString();
    }

    public String getTaskCountString() {
        return String.format("You have %d tasks", this.tasks.size());
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    public String toString() {
        StringBuilder taskString = new StringBuilder();
        int len = tasks.size();

        for (int i = 0; i < len - 1; i ++) {
            taskString.append(i + 1 + ". " + tasks.get(i).toString() + "\n");
        }

        taskString.append(len + ". " + tasks.get(len - 1).toString());

        return taskString.toString();
    }
}