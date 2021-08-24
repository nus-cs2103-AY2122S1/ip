package lania;

import lania.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArrayList;

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public void update(Task t) {
        taskArrayList.add(t);
    }

    public void complete(int i) {
        i--;
        taskArrayList.get(i).markAsDone();
    }

    public Task remove(int i) {
        i--;
        Task t = taskArrayList.get(i);
        taskArrayList.remove(i);
        return t;
    }

    public int size() {
        return taskArrayList.size();
    }

    public Task get(int i) {
        return taskArrayList.get(i);
    }
}
