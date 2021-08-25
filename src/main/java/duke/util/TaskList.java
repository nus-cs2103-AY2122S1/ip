package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int length() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void done(int index) {
        Task temp = tasks.get(index);
        temp.markAsDone();
        tasks.set(index, temp);
    }

    public void delete(int index) {
        tasks.remove(index);
    }
}
