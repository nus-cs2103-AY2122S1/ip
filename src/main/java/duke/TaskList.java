package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int taskId) throws DukeException {
        try {
            return this.tasks.get(taskId - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Wrong index input.");
        }

    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int taskId) throws DukeException {
        try {
            return this.tasks.remove(taskId - 1);
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new DukeException("That task does not exist.");
        }
    }

    public Task markAsCompleted(int taskId) throws DukeException {
        try {
            Task currentTask = this.tasks.get(taskId - 1);
            Task completedTask = currentTask.complete();
            this.tasks.set(taskId - 1, completedTask);
            return completedTask;
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new DukeException("That task does not exist.");
        }
    }

    @Override()
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int j = 1; j <= this.size() ; j++) {
            String line = null;
            try {
                line = j + "." + this.get(j).toString();
            } catch (DukeException e) {
                e.printStackTrace();
            }
            result.append(line);
            if (j < this.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }

}
