package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TaskList {
    private final List<Task> store;

    TaskList() {
        store = new ArrayList<>();
    }

    public int size() {
        return store.size();
    }

    public Task get(int taskId) throws DukeException {
        try {
            return this.store.get(taskId - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Wrong index input.");
        }

    }

    public void addTask(Task task) {
       this.store.add(task);
    }

    public Task deleteTask(int taskId) throws DukeException {
        try {
            return this.store.remove(taskId - 1);
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new DukeException("That task does not exist.");
        }
    }

    public Task markAsCompleted(int taskId) throws DukeException {
        try {
            Task currentTask = this.store.get(taskId - 1);
            Task completedTask = currentTask.complete();
            this.store.set(taskId - 1, completedTask);
            return completedTask;
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new DukeException("That task does not exist.");
        }
    }

    public TaskList filter(String input) {
        TaskList taskList = new TaskList();
        for ( int i = 0; i < this.size() ; i++ ) {
            Task task = this.store.get(i);
            String nameOfTask = task.getName();
            if (nameOfTask.contains(input)) {
                taskList.addTask(task);
            }
        }
        return taskList;
    }

    @Override()
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int j = 1; j <= this.size() ; j++) {
            String line = "";
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
