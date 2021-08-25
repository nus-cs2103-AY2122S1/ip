package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int index) {
        return this.taskList.remove(index);
    }

    public int size() {
        return this.taskList.size();
    }
}
