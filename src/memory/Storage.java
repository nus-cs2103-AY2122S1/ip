package memory;

import java.util.Arrays;

public class Storage {
    private final Task[] storage;
    private int index;

    public Storage() {
        storage = new Task[100];
        index = 0;
    }

    public Task push(String value) {
        storage[index] = new Task(value);
        index += 1;
        return storage[index - 1];
    }

    public Task push(String[] args) {
        String command = args[0];
        switch (command) {
            case "todo":
                storage[index] = new ToDo(args[1]);
                break;
            case "deadline":
                storage[index] = new Deadline(args[1], args[2]);
                break;
            case "event":
                storage[index] = new Event(args[1], args[2]);
                break;
        }
        index += 1;
        return storage[index - 1];
    }

    public Task markDone(int i) {
        storage[i].markDone();
        return storage[i];
    }

    public Task[] getStorage() {
        return Arrays.copyOfRange(storage, 0, index);
    }

    public Task getTaskByIdx(int id) {
        return storage[id];
    }

    public int numTasks() {
        return index;
    }
}