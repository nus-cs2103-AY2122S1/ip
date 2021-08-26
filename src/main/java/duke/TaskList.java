package duke;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {
    private ArrayList<Task> list;

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

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }
}
