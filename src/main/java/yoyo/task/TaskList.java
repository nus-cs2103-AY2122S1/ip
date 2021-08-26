package yoyo.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    @Override
    public boolean equals(Object o) {
        @SuppressWarnings("unchecked")
        TaskList otherTaskList = (TaskList) o;
        return this.tasks.equals(otherTaskList.tasks);
    }


}
