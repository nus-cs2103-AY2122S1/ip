package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Task mostRecent;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int noOfTask() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        // Precompute information required
        int length = this.tasks.size();
        String list = "";

        // Iterate through all the tasks in the list
        for (int i = 0; i < length; i++) {
            list = list + String.valueOf(i + 1) + ". " + this.tasks.get(i) +"\n";
        }

        // Return result
        return list;
    }

    public Task getMostRecent() {
        return this.mostRecent;
    }

    public void add(Task task) {
        this.tasks.add(task);
        this.mostRecent = task;
    }

    public void delete(int taskIndex) throws DukeException {
        int length = this.tasks.size();
        if (taskIndex > length || taskIndex <= 0) {
            throw new DukeException("you have to choose a number based on the list!");
        } else {
            Task removed = this.tasks.get(taskIndex - 1);
            this.mostRecent = removed;

            this.tasks.remove(taskIndex - 1);
        }
    }

    public void done(int taskIndex) throws DukeException {
        int length = this.tasks.size();
        if (taskIndex > length || taskIndex <= 0) {
            throw new DukeException("you have to choose a number based on the list!");
        } else {
            Task task = this.tasks.get(taskIndex - 1);
            task.setDone();
            this.mostRecent = task;
        }
    }
}
