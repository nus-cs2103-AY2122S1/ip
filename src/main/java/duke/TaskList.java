package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markTaskAsDone(int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("markTaskAsDone index out of range.");
        }
        Task task = tasks.get(i);
        task.markAsDone();
        return task;
    }

    public Task removeTask(int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("removeTask index out of range.");
        }
        Task task = tasks.get(i);
        tasks.remove(i);
        return task;
    }

    /**
     * Searches for tasks that matches keyword
     * @param key keyword used for searching tasks
     * @return ArrayList of matching tasks
     */
    public ArrayList<Task> findTasks(String key) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(key)) {
                result.add(task);
            }
        }
        return result;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }
}
