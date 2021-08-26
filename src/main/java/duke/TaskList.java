package duke;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public TaskList find(String query) {
        TaskList output = new TaskList();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).toString().contains(query)) {
                output.add(this.get(i));
            }
        }
        return output;
    }
}
