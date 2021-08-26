package duke.task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String serialize() {
        String serializedData = "";

        for (Task task : this.taskList) {
            serializedData += task.serialize() + "\n";
        }

        return serializedData;
    }

    public static TaskList deserialize(List<String> list) throws IllegalArgumentException, DateTimeParseException {
        TaskList taskList = new TaskList();

        for (String data : list) {
            taskList.addTask(Task.deserialize(data));
        }

        return taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public void markDone(int index) {
        this.taskList.get(index).markAsDone();
    }

    public void markNotDone(int index) {
        this.taskList.get(index).markAsNotDone();
    }

    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = taskList.get(i);

            s += (i + 1) + ". " + task + "\n";
        }

        return s;
    }
}
