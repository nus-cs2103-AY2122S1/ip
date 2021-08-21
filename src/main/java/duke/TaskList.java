package duke;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task delete(int index) {
        Task t = tasks.get(index);
        tasks.remove(index);
        return t;
    }

    public Task done(int index) {
        Task t = tasks.get(index);
        t.markAsDone();
        return t;
    }

    public String list() {
        // list out all tasks
        int count = 1;
        StringBuilder result = new StringBuilder("     Here are the tasks in your list:\n");
        for (Task task : tasks) {
            result.append(String.format("     %d.%s\n", count, task));
            count++;
        }
        return result.toString();
    }

    public int size() {
        return tasks.size();
    }

    public String find(String keyword) {
        int count = 1;
        StringBuilder result = new StringBuilder("     Here are the matching tasks in your list:\n");
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                result.append(String.format("     %d.%s\n", count, task));
                count++;
            }
        }
        return result.toString();
    }
}
