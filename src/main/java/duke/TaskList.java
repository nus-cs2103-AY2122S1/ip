package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public boolean addTaskFromRepr(String repr) throws DukeException {
            Task t = Task.fromRepr(repr);
            return tasks.add(t);
    }

    public boolean addTask(Task t) {
        return tasks.add(t);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            s += String.format("%d.%s\n", i + 1, tasks.get(i));
        }
        return s;
    }

    public Task markAsComplete(int i)  {
        Task t = tasks.get(i - 1);
        t.markAsComplete();
        return t;
    }

    public Task deleteTask(int i)  {
        return tasks.remove(i - 1);
    }

    public int numberOfTasks() {
        return tasks.size();
    }

    public ArrayList<String> toRepr() {
        ArrayList<String> arr = new ArrayList<>();
        for (Task t: tasks) {
            arr.add(t.getRepr());
        }
        return arr;
    }

}
