package duke.task;

import java.util.ArrayList;
import java.util.ListIterator;

@SuppressWarnings("checkstyle:Regexp")
public class TaskList {

    private ArrayList<Task> tasks;
    private ArrayList<Task> prev;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.prev = new ArrayList<>(tasks);
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task remove(int num) {
        return tasks.remove(num);
    }

    public boolean add(Task tsk) {
        return tasks.add(tsk);
    }

    public Task getTask(int num) {
        return tasks.get(num);
    }

    public int numTasks() {
        return tasks.size();
    }

    public void markTask(int num) {
        tasks.get(num).markDone();
    }

    public void clear() {
        // clears taskList and saves the previous one to prevList
        prev = new ArrayList<>(tasks);
        tasks = new ArrayList<>();
    }

    public void restore() {
        tasks = new ArrayList<>(prev);
    }

    public String listTaskArr() {
        StringBuilder res = new StringBuilder();
        ListIterator<Task> iter = tasks.listIterator();

        while (iter.hasNext()) {
            res.append(iter.nextIndex() + 1).append(".").append(iter.next());
            if (iter.hasNext()) {
                res.append("\n");
            }
        }

        return res.toString();
    }

    public ListIterator<Task> makeIterator() {
        return tasks.listIterator();
    }
}
