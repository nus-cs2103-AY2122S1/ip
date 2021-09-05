package duke;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
      return tasks.remove(index);
    }

    public Task markTaskDone(int index) {
        Task doneTask = tasks.get(index);
        doneTask.markAsDone();
        return doneTask;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        String str = "";
        int ctr = 1;
        for (Task t : tasks) {
            str += ctr + ". " + t + "\n";
            ctr++;
        }
        return str;
    }
}
