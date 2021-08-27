package TiTi.util;

import TiTi.task.Task;

import java.util.ArrayList;


public class TaskList {

    protected ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public Task get(int taskNumber) {
        return list.get(taskNumber);
    }

    public void add(Task task) {
        list.add(task);
    }

    public int size() {
        return list.size();
    }

    public void remove(int taskNumber) {
        list.remove(taskNumber);
    }

}
