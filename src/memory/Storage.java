package memory;

import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
    private final ArrayList<Task> storage;
    private int index;

    public Storage() {
        storage = new ArrayList<>();
        index = 0;
    }

    public Task push(String value) {
        storage.add(new Task(value));
        index += 1;
        return storage.get(index - 1);
    }

    public Task push(String[] args) {
        String command = args[0];
        switch (command) {
            case "todo":
                storage.add(new ToDo(args[1]));
                break;
            case "deadline":
                storage.add(new Deadline(args[1], args[2]));
                break;
            case "event":
                storage.add(new Event(args[1], args[2]));
                break;
        }
        index += 1;
        return storage.get(index - 1);
    }

    public Task markDone(int i) {
        storage.get(i).markDone();
        return storage.get(i);
    }

    public Task[] getStorage() {
        return storage.toArray(new Task[0]);
    }

    public Task getTaskByIdx(int id) {
        return storage.get(id);
    }

    public Task deleteTaskByIdx(int id) {
        index -= 1;
        return storage.remove(id);
    }

    public int numTasks() {
        return index;
    }
}