package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += String.valueOf(i + 1) + ". " + tasks.get(i) + "\n\t ";
        }
        return output;
    }

    public String toStringForFile() {
        String output = "";
        for (Task t : tasks) {
            output += t.toStringForFile() + System.lineSeparator();
        }
        return output;
    }

    public Task markAsDone(int index) {
        Task t = tasks.get(index - 1);
        t.markAsDone();
        return t;
    }

    public int getSize() {
        return tasks.size();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task delete(int index) {
        Task t = tasks.get(index - 1);
        tasks.remove(t);
        return t;
    }
}
