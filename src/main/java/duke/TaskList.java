package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Locale;

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

    public String searchTask(String searchWord) {
        String result = "";
        int counter = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task nextTask = taskList.get(i);
            if (nextTask.getDescription().toLowerCase().contains(searchWord.toLowerCase())) {
                result += counter + "." + nextTask.toString() + "\n";
                counter++;
            }
        }
        result = result.trim();
        return result;
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
