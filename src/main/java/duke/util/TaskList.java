package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {

    private final ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasks) {
        list = tasks;
    }

    public TaskList() {
        list = new ArrayList<>();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void add(Task task) {
        list.add(task);
    }

    public void delete(int index) {
        try {
            list.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e);
        }
    }

    public int size() {
        return list.size();
    }

    public void forEach(Consumer<? super Task> action) {
        list.forEach(action);
    }

}
