package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    Task delete(int index) {
        return tasks.remove(index);
    }

    int getSize() {
        return tasks.size();
    }

    Task get(int index) {
        return tasks.get(index);
    }
}
