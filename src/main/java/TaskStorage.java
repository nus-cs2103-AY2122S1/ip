import java.util.NoSuchElementException;

public class TaskStorage {

    private Task[] storage = new Task[100];
    private int current = -1; // index for current item in the storage

    // Adds a new task to the storage and returns a confirmation message
    public String add(Task task) {
        storage[++current] = task;
        return "Added: " + task.getDescription();
    }

    // Checks if the storage is empty
    public boolean isEmpty() {
        return current == -1;
    }

    // Marks a task as done, and returns a confirmation message
    public String markDone(int ind) {
        if (storage[ind] == null) {
            throw new NoSuchElementException("There is no such task!");
        }
        storage[ind].markDone();

        return "Nice! I've marked this task as done: \n  " + storage[ind].toString();
    }

    // Gets a task from the storage
    public Task get(int ind) {
        if (storage[ind] == null) {
            throw new NoSuchElementException("There is no task at the given index: " + ind);
        }
        return storage[ind];
    }

    // Lists out all tasks from the storage in order of index
    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i <= current; i++) {
            result += (String.valueOf(i + 1) + "." + storage[i].toString() + "\n");
        }
        return result;
    }
}
