package memory;

import exception.InvalidCommandException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

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

    public Task markDone(int id) {
        if (id >= index || id < 0) {
            throw new InvalidCommandException("Provided Index is out of bounds! Given index is " +
                    (id + 1) + " but there are " + index + " elements in the list");
        }
        storage.get(id).markDone();
        return storage.get(id);
    }

    public Task[] getStorage() {
        return storage.toArray(new Task[0]);
    }

    public Task getTaskByIdx(int id) {
        if (id >= index || id < 0) {
            throw new InvalidCommandException("Provided Index is out of bounds! Given index is " +
                    (id + 1) + " but there are " + index + " elements in the list");
        }
        return storage.get(id);
    }

    public Task deleteTaskByIdx(int id) {
        if (id >= index || id < 0) {
            throw new InvalidCommandException("Provided Index is out of bounds!");
        }
        index -= 1;
        return storage.remove(id);
    }

    public int numTasks() {
        return index;
    }
}