package duke;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Encapsulates a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Creates the list.
     *
     * @param prevTasks the list of saved tasks from save file if any.
     */
    public TaskList(ArrayList<Task> prevTasks) {
        this.list = prevTasks;
    }

    public void add(Task newTask) {
        list.add(newTask);
    }

    public Task delete(int index) {
        return list.remove(index);
    }

    public void forEach(Consumer<? super Task> action) {
        list.forEach(action);
    }

    public Task completeTask(int index) {
        list.get(index).complete();
        return list.get(index);
    }

    /**
     * Provides the number of tasks on this list.
     *
     * @return size of the list.
     */
    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }
}
