package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList = new ArrayList<>();

    public TaskList() {}

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int taskPosition) {
        taskList.remove(taskPosition);
    }

    public Task getTask(int taskPosition) {
        return taskList.get(taskPosition);
    }

    public int size() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
