package main.java.bot;

import main.java.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    TaskList() {
        this.list = new ArrayList<>();
    }

    TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void setTask(int index, Task task) {
        this.list.set(index, task);
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void removeTask(int index) {
        this.list.remove(index);
    }

    public int getSize() { return this.list.size(); }
}
