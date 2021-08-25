package duke.data;

import duke.commands.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task delete(int index) {
        Task toDelete = taskList.get(index);
        this.taskList.remove(index);
        return toDelete;
    }

    public String markDone(int index) {
        return this.taskList.get(index).markDone();
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public ArrayList<Task> getEntire() {
        return this.taskList;
    }
}
