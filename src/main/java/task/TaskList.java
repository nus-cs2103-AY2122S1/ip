package task;

import exceptions.DukeException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    private final String ERROR_OUT_OF_INDEX = "Error: Task out of list. Use 'list' to check your task index.";

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int taskIndex) throws DukeException {
        try {
            return tasks.get(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_OUT_OF_INDEX);
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addTask(Task task, int taskIndex) {
        tasks.add(taskIndex - 1, task);
    }

    public Task removeTask(int taskIndex) throws DukeException {
        try {
            return tasks.remove(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_OUT_OF_INDEX);
        }
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public int length() {
        return tasks.size();
    }

    public String getFileString(String delimiter, String done, String notDone) {
        StringBuilder builder = new StringBuilder();
        for (Task task : tasks) {
            String appendedString = task.getTaskFileString(delimiter, done, notDone) + "\n";
            builder.append(appendedString);
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            builder.append(i + 1).append(" ").append(tasks.get(i).toString()).append("\n");
        }
        return builder.toString();
    }
}
