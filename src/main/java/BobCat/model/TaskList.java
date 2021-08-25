package BobCat.model;

import BobCat.exception.InvalidOpsException;
import BobCat.model.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> storage;
    private int index;

    public TaskList() {
        storage = new ArrayList<>();
        index = 0;
    }

    public Task push(Task t) {
        storage.add(t);
        index += 1;
        return storage.get(index - 1);
    }

    public Task markDone(int id) {
        if (id >= index || id < 0) {
            throw new InvalidOpsException("Provided Index is out of bounds! Given index is " +
                    (id + 1) + " but there are " + index + " elements in the list");
        }
        storage.get(id).markDone();
        return storage.get(id);
    }

    public Task[] getAllTasks() {
        return storage.toArray(new Task[0]);
    }

    public Task deleteTaskByIdx(int id) {
        if (id >= index || id < 0) {
            throw new InvalidOpsException("Provided Index is out of bounds!");
        }
        index -= 1;
        return storage.remove(id);
    }

    public int numTasks() {
        return index;
    }

}
