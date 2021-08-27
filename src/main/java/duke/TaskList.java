package duke;

import java.util.ArrayList;

public class TaskList {

    /** Stores tasks in an array list */
    private ArrayList<Task> lst;

    public TaskList() {
        lst = new ArrayList<>();
    }

    public boolean addTask(Task t) {
        return lst.add(t);
    }

    public Task getTask(int index) {
        return lst.get(index);
    }

    public Task removeTask(int index) {
        return lst.remove(index);
    }

    public int size() {
        return lst.size();
    }

    public ArrayList<Task> getTaskList() {
        return lst;
    }
}
