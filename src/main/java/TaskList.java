import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> store;

    TaskList() {
        store = new ArrayList<>();
    }

    public int size() {
        return store.size();
    }

    public Task get(int taskId) {
        return this.store.get(taskId - 1);
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

    @Override()
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int j = 1; j <= this.size() ; j++) {
            String line = j + "." + this.get(j).toString();
            result.append(line);
            if (j < this.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }

}
